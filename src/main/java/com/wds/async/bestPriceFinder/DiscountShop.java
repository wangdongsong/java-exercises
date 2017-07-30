package com.wds.async.bestPriceFinder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.wds.async.bestPriceFinder.Util.delay;

/**
 * Created by wangdongsong1229@163.com on 2017/7/30.
 */
public class DiscountShop {
    private static List<DiscountShop> shops = Arrays.asList(new DiscountShop("BestPrice"), new DiscountShop("LetsSaveBig"), new DiscountShop("MyFavoriteShop"), new DiscountShop("buyItAll"));
    private String name;
    private final Random random;

    private final static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(String[] args) {
        DiscountShop disShop = new DiscountShop("test");
        disShop.findPrices("iPhone8").forEach((s) -> System.out.println(s));

        //响应式测试
        long start = System.nanoTime();
        CompletableFuture[] futures = disShop.findPricesStream("iPhon8")
                .map(f -> f.thenAccept(s -> System.out.println(s + "(done in" + ((System.nanoTime() - start)/ 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        //等待所有执行完成
        CompletableFuture.allOf(futures).join();
        //其中一个完成
        CompletableFuture.anyOf(futures).join();
    }

    public List<String> findPrices(String product){
        return shops.stream().map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 响应式优化
     * @param product
     * @return
     */
    public Stream<CompletableFuture<String>> findPricesStream(String product){
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

    }

    /**
     * 异步
     * @param product
     * @return
     */
    public List<String> findFuturePrices(String product){
        ExchangeService es = new ExchangeService();
        List<CompletableFuture<String>> priceFutures = shops.stream()
                //以异步方式取得每个shop中指定产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务构造期望的Future申请折扣
                //thenCompose方法允许对两个异步操作进行流水线，第一个操作完成时，将其结果作为参数传递给第二个操作。
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        //另一种常见场景
        //需要将两个完全不相干的CompletableFuture对象的结果整合起来，不希望在等到第一个任务完全结束才开始第二项任务，使用thenCombine方法
        List<CompletableFuture<Double>> futurePriceInUSD =shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getDoublePrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(
                                () -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                                (price, rate) -> price * rate)).collect(Collectors.toList());

        //等待流中的所有Future执行完毕并提取各自的返回值
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public DiscountShop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double getDoublePrice(String product){
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }


}
