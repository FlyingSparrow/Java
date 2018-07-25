package com.sparrow.pizza.ingrerdientfactory.impl;

import com.sparrow.pizza.ingrerdient.cheese.Cheese;
import com.sparrow.pizza.ingrerdient.cheese.impl.MozzarellaCheese;
import com.sparrow.pizza.ingrerdient.clam.Clams;
import com.sparrow.pizza.ingrerdient.clam.impl.FrozenClams;
import com.sparrow.pizza.ingrerdient.dough.Dough;
import com.sparrow.pizza.ingrerdient.dough.impl.ThickCrustDough;
import com.sparrow.pizza.ingrerdient.pepperoni.Pepperoni;
import com.sparrow.pizza.ingrerdient.pepperoni.impl.SlicedPepperoni;
import com.sparrow.pizza.ingrerdient.sauce.Sauce;
import com.sparrow.pizza.ingrerdient.sauce.impl.PlumTomatoSauce;
import com.sparrow.pizza.ingrerdient.veggie.Veggies;
import com.sparrow.pizza.ingrerdient.veggie.impl.BlackOlives;
import com.sparrow.pizza.ingrerdient.veggie.impl.Eggplant;
import com.sparrow.pizza.ingrerdient.veggie.impl.Spinach;
import com.sparrow.pizza.ingrerdientfactory.PizzaIngredientFactory;

/**
 * 芝加哥披萨原料工厂
 *
 * @author wangjianchun
 * @create 2018/7/24
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new BlackOlives(), new Spinach(), new Eggplant()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FrozenClams();
    }
}
