package com.sparrow;

import com.sparrow.adapter.TurkeyAdapter;
import com.sparrow.duck.Duck;
import com.sparrow.duck.impl.MallardDuck;
import com.sparrow.turkey.impl.WildTurkey;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public class DuckTestDrive {

    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("\nThe Duck says...");
        testDuck(mallardDuck);

        System.out.println("\nThe TurkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    private static void testDuck(Duck duck){
        duck.quack();
        duck.fly();
    }
}
