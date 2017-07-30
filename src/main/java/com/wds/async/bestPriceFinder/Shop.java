package com.wds.async.bestPriceFinder;

import com.wds.concurrency.future.FutureTest;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * Created by wangdongsong1229@163.com on 2017/7/30.
 */
public class Shop {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Shop shop = new Shop();
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
     * 同步阻塞：根据商品获取价格
     * @param product
     * @return
     */
    public double getPrice(String product){
        return calculatePrice(product);
    }

    /**
     * 异步阻塞：根据商品获取价格
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            //异常处理，若发生异常，会导致应用线程一直阻塞
            try{
                double price = calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
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

}
