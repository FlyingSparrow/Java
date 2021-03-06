package com.sparrow.pizza.impl;

import com.sparrow.pizza.Pizza;
import com.sparrow.pizza.ingrerdientfactory.PizzaIngredientFactory;

/**
 * @author wangjianchun
 * @create 2018/7/24
 */
public class VeggiePizza extends Pizza {

    private PizzaIngredientFactory pizzaIngredientFactory;

    public VeggiePizza(PizzaIngredientFactory pizzaIngredientFactory){
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing "+name);
        dough = pizzaIngredientFactory.createDough();
        sauce = pizzaIngredientFactory.createSauce();
        cheese = pizzaIngredientFactory.createCheese();
        clam = pizzaIngredientFactory.createClam();
    }
}
