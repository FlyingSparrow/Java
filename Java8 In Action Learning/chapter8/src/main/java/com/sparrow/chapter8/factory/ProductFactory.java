package com.sparrow.chapter8.factory;

import com.sparrow.chapter8.factory.impl.Bond;
import com.sparrow.chapter8.factory.impl.Loan;
import com.sparrow.chapter8.factory.impl.Stock;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class ProductFactory {

    public static Product createProduct(String name){
        switch (name){
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new RuntimeException("No such product "+name);
        }
    }

}
