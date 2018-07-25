package com.sparrow.pizzastore.impl;

import com.sparrow.pizza.Pizza;
import com.sparrow.pizza.impl.CheesePizza;
import com.sparrow.pizza.impl.ClamPizza;
import com.sparrow.pizza.impl.PepperoniPizza;
import com.sparrow.pizza.impl.VeggiePizza;
import com.sparrow.pizza.ingrerdientfactory.PizzaIngredientFactory;
import com.sparrow.pizza.ingrerdientfactory.impl.ChicagoPizzaIngredientFactory;
import com.sparrow.pizzastore.PizzaStore;

/**
 * @author wangjianchun
 * @create 2018/7/24
 */
public class ChicagoStylePizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;

        PizzaIngredientFactory ingredientFactory = createPizzaIngredientFactory();

        if(type.equals("cheese")){
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("Chicago Style Cheese Pizza");
        }else if(type.equals("pepperoni")){
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("Chicago Style Veggie Pizza");
        }else if(type.equals("clam")){
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("Chicago Style Clam Pizza");
        }else if(type.equals("veggie")){
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("Chicago Style Pepperoni Pizza");
        }else{
            throw new RuntimeException("Invalid pizza type");
        }

        return pizza;
    }

    @Override
    protected PizzaIngredientFactory createPizzaIngredientFactory() {
        return new ChicagoPizzaIngredientFactory();
    }
}
