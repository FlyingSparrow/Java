package com.sparrow.pizzastore;

import com.sparrow.pizza.Pizza;
import com.sparrow.pizza.ingrerdientfactory.PizzaIngredientFactory;

/**
 * 披萨店
 *
 * @author wangjianchun
 * @create 2018/7/24
 */
public abstract class PizzaStore {

    public final Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);

    protected abstract PizzaIngredientFactory createPizzaIngredientFactory();

}
