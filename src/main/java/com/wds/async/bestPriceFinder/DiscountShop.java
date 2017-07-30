package com.wds.async.bestPriceFinder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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
    }

    public List<String> findPrices(String product){
        return shops.stream().map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
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

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }


}
