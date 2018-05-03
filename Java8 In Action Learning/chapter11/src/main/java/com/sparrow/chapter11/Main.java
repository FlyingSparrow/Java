package com.sparrow.chapter11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author wangjianchun
 * @create 2018/4/21
 */
public class Main {

    public static void main(String[] args) {
//        getPriceDemo();
//        calculateAppropriateThreadPoolSize();
        getPrices();
        getPricesAsync();
        getPricesStream();
    }

    private static int calculateAppropriateThreadPoolSize(){
        //CPU数量
        int cpuCount = Runtime.getRuntime().availableProcessors();
        //期望的CPU利用率
        double cpuUtilization = 0.8D;
        int result = Double.valueOf((cpuCount * cpuUtilization*(1+0.2))).intValue();
        System.out.println("appropriate thread poolSize: "+result);
        return result;
    }

    private static void getPriceDemo(){
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Invocation returned after "+invocationTime+" msecs");

        //执行更多任务，比如查询其它商店
        doSomethingElse();
        //在计算商品价格的同时
        try {
            //从 Future 对象中读取价格，如果价格未知，会发生阻塞
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Price returned after "+retrievalTime+" msecs");
    }

    private static void getPrices(){
        long start = System.nanoTime();
        System.out.println(new Price().findPrices("myPhone27S"));
        long duration = ((System.nanoTime()-start)/1_000_000);
        System.out.println("Done in "+duration+" msecs");
    }

    private static void getPricesAsync(){
        long start = System.nanoTime();
        System.out.println(new Price().findPricesAsync("myPhone27S"));
        long duration = ((System.nanoTime()-start)/1_000_000);
        System.out.println("Done in "+duration+" msecs");
    }

    private static void getPricesStream(){
        long start = System.nanoTime();

        CompletableFuture[] futures = new Price().findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(s -> System.out.println(
                        s+" (done in "+((System.nanoTime()-start)/1_000_000)+" msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "+((System.nanoTime()-start)/1_000_000)+" msecs");
    }

    private static void doSomethingElse() {
    }
}
