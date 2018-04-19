package com.sparrow.chapter8.factory;

import com.sparrow.chapter8.factory.impl.Bond;
import com.sparrow.chapter8.factory.impl.Loan;
import com.sparrow.chapter8.factory.impl.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author wangjianchun
 * @create 2018/4/19
 */
public class ProductFactoryWithJava8 {

    private static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct(String name){
        Supplier<Product> productSupplier = map.get(name);
        if(productSupplier != null){
            return productSupplier.get();
        }
        throw new IllegalArgumentException("No such product "+name);
    }

}
