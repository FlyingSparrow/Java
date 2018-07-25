package com.sparrow.pizza.ingrerdientfactory;

import com.sparrow.pizza.ingrerdient.cheese.Cheese;
import com.sparrow.pizza.ingrerdient.clam.Clams;
import com.sparrow.pizza.ingrerdient.dough.Dough;
import com.sparrow.pizza.ingrerdient.pepperoni.Pepperoni;
import com.sparrow.pizza.ingrerdient.sauce.Sauce;
import com.sparrow.pizza.ingrerdient.veggie.Veggies;

/**
 * 披萨原料工厂
 *
 * @author wangjianchun
 * @create 2018/7/24
 */
public interface PizzaIngredientFactory {

    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}
