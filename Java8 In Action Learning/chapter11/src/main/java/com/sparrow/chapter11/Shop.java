package com.sparrow.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author wangjianchun
 * @create 2018/4/21
 */
public class Shop {

    private static final Random random = new Random();

    private String name;

    public Shop(String name){
        this.name = name;
    }

    /*public double getPrice(String product){
        return calculatePrice(product);
    }*/

    public Future<Double> getPriceAsync(String product){
        /*CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            try {
                double price = calculatePrice(product);
                completableFuture.complete(price);

                //用于模拟价格计算过程中出现异常的情况
//                throw new RuntimeException("product not available");
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }).start();
        return completableFuture;*/

        //下面这行代码和上面代码是完全等价的
        return CompletableFuture.supplyAsync(()-> calculatePrice(product));
    }

    public String getPrice(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        Utils.randomDelay();
        return random.nextDouble()*product.charAt(0)+product.charAt(1);
    }

    public String getName() {
        return name;
    }
}
