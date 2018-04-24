package com.sparrow.chapter11;

import java.text.DecimalFormat;

/**
 * @author wangjianchun
 * @create 2018/4/22
 */
public class Discount {

    public enum Code {

        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote){
        return quote.getShopName()+" price is "+Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code){
        Utils.delay();
        return format(price*(100-code.percentage)/100);
    }

    private static double format(double value) {
        return Double.parseDouble(new DecimalFormat("#.##").format(value));
    }
}
