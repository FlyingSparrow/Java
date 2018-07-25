package com.sparrow.pizza;

import com.sparrow.pizzastore.PizzaStore;
import com.sparrow.pizzastore.impl.ChicagoStylePizzaStore;
import com.sparrow.pizzastore.impl.NYPizzaStore;

/**
 * @author wangjianchun
 * @create 2018/7/24
 */
public class PizzaTestDrive {

    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NYPizzaStore();
        PizzaStore chicagoPizzaStore = new ChicagoStylePizzaStore();

        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a "+pizza.getName()+"\n");

        pizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println("Joel ordered a "+pizza.getName()+"\n");
    }
}
