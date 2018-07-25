package com.sparrow.pizza;

import com.sparrow.pizza.ingrerdient.cheese.Cheese;
import com.sparrow.pizza.ingrerdient.clam.Clams;
import com.sparrow.pizza.ingrerdient.dough.Dough;
import com.sparrow.pizza.ingrerdient.pepperoni.Pepperoni;
import com.sparrow.pizza.ingrerdient.sauce.Sauce;
import com.sparrow.pizza.ingrerdient.veggie.Veggies;

/**
 * 披萨
 *
 * @author wangjianchun
 * @create 2018/7/24
 */
public abstract class Pizza {

    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected Veggies[] veggies;
    protected Cheese cheese;
    protected Pepperoni pepperoni;
    protected Clams clam;

    /**
     * 准备
     */
    public abstract void prepare();

    /**
     * 烘烤
     */
    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    /**
     * 切片
     */
    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    /**
     * 装盒
     */
    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
