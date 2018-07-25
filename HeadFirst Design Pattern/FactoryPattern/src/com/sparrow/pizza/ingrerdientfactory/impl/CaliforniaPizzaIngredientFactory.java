package com.sparrow.pizza.ingrerdientfactory.impl;

import com.sparrow.pizza.ingrerdient.cheese.Cheese;
import com.sparrow.pizza.ingrerdient.cheese.impl.GoatCheese;
import com.sparrow.pizza.ingrerdient.clam.Clams;
import com.sparrow.pizza.ingrerdient.clam.impl.Calamari;
import com.sparrow.pizza.ingrerdient.dough.Dough;
import com.sparrow.pizza.ingrerdient.dough.impl.VeryThinCrust;
import com.sparrow.pizza.ingrerdient.pepperoni.Pepperoni;
import com.sparrow.pizza.ingrerdient.sauce.Sauce;
import com.sparrow.pizza.ingrerdient.sauce.impl.BruschettaSauce;
import com.sparrow.pizza.ingrerdient.veggie.Veggies;
import com.sparrow.pizza.ingrerdient.veggie.impl.RedPepper;
import com.sparrow.pizza.ingrerdientfactory.PizzaIngredientFactory;

/**
 * 加利福尼亚披萨原料工厂
 *
 * @author wangjianchun
 * @create 2018/7/24
 */
public class CaliforniaPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new VeryThinCrust();
    }

    @Override
    public Sauce createSauce() {
        return new BruschettaSauce();
    }

    @Override
    public Cheese createCheese() {
        return new GoatCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[]{new RedPepper()};
    }

    @Override
    public Pepperoni createPepperoni() {
        return new Pepperoni();
    }

    @Override
    public Clams createClam() {
        return new Calamari();
    }
}
