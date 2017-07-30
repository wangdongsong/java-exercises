package com.wds.async.bestPriceFinder;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by wangdongsong1229@163.com on 2017/7/30.
 */
public class FuncShop {

    private static List<FuncShop> shops = Arrays.asList(new FuncShop("BestPrice"), new FuncShop("LetsSaveBig"), new FuncShop("MyFavoriteShop"), new FuncShop("buyItAll"));
    private String name;

    private final static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (r) -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });


    public FuncShop(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //baseTest();
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        long pStart = System.nanoTime();
        System.out.println(findPrices("myPhone27s"));
        long pDuration = (System.nanoTime() - pStart) / 1_000_000;
        System.out.println("Parallel Done in " + pDuration + " msecs");

        long p2Start = System.nanoTime();
        System.out.println(findPricesFuture("myPhone27s"));
        long p2Duration = (System.nanoTime() - p2Start) / 1_000_000;
        System.out.println("Parallel Done in " + p2Duration + " msecs");
    }

    public static List<String> findPrices(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    public static List<String> parallelFindPrices(String product){
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 异步方式计算商品价格
     *
     * 特别说明：使用两个不同的Stream流水线，而不是在同一个处理流的流水线上一个接一个地放置两个Map操作。
     *
     * 如果在单一流水线中处理，发向不同商家的请求只能同步、顺序执行才会成功
     *
     * @param product
     * @return
     */
    public static List<String> findPricesFuture(String product){
        //以异步方式计算每种商品的价格
        //List<CompletableFuture<String>> priceFutrues = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product))).collect(Collectors.toList());

        List<CompletableFuture<String>> priceFutrues = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor)).collect(Collectors.toList());

        //等待所有异常操作结束
        return priceFutrues.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private static void baseTest() throws InterruptedException, ExecutionException {
        FuncShop shop = new FuncShop("test");
        long start = System.nanoTime();

        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        //从Future对象中读取价格，如果价格未知，会发生阻塞
        double price = futurePrice.get();
        System.out.printf("Price is %.2f%n", price);

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("price returned after " + retrievalTime + " msecs");
    }


    /**
     * supplyAsync创建CompletableFuture
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
