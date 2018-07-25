package com.sparrow.pizza.ingrerdientfactory.impl;

import com.sparrow.pizza.ingrerdient.cheese.Cheese;
import com.sparrow.pizza.ingrerdient.cheese.impl.ReggianoCheese;
import com.sparrow.pizza.ingrerdient.clam.Clams;
import com.sparrow.pizza.ingrerdient.clam.impl.FreshClams;
import com.sparrow.pizza.ingrerdient.dough.Dough;
import com.sparrow.pizza.ingrerdient.dough.impl.ThinCrustDough;
import com.sparrow.pizza.ingrerdient.pepperoni.Pepperoni;
import com.sparrow.pizza.ingrerdient.pepperoni.impl.SlicedPepperoni;
import com.sparrow.pizza.ingrerdient.sauce.Sauce;
import com.sparrow.pizza.ingrerdient.sauce.impl.MarinaraSauce;
import com.sparrow.pizza.ingrerdient.veggie.Veggies;
import com.sparrow.pizza.ingrerdient.veggie.impl.Garlic;
import com.sparrow.pizza.ingrerdient.veggie.impl.Mushroom;
import com.sparrow.pizza.ingrerdient.veggie.impl.Onion;
import com.sparrow.pizza.ingrerdient.veggie.impl.RedPepper;
import com.sparrow.pizza.ingrerdientfactory.PizzaIngredientFactory;

/**
 * 纽约披萨原料工厂
 * 
 * @author wangjianchun
 * @create 2018/7/24
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
