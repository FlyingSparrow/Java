package com.sparrow.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

/**
 * @author wangjianchun
 * @create 2018/4/21
 */
public class Price {

    private static final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"),
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    //使用守护线程，不会阻止程序的关停
                    thread.setDaemon(true);
                    return thread;
                }
            });

/*    public List<String> findPrices(String product){
        //使用 CompletableFuture 以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)), executor)).collect(toList());
        //等待所有异步操作结束
        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
        *//*return shops.stream().map(shop -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product))).collect(toList());*//*
    }*/

    public List<String> findPrices(String product){
        return shops.stream().map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    public List<String> findPricesAsync(String product){
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                    CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote), executor)))
                .collect(toList());

        return priceFutures.stream().map(CompletableFuture::join)
                .collect(toList());
    }
}
