package com.sparrow.chapter8.chainofreponsibility;

import com.sparrow.chapter8.chainofreponsibility.impl.HeaderTextProcessing;
import com.sparrow.chapter8.chainofreponsibility.impl.SpellCheckerProcessing;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author wangjianchun
 * @create 2018/4/13
 */
public class Main {

    public static void main(String[] args) {
        test();
        testWithLambda();
    }

    private static void test(){
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);
    }

    private static void testWithLambda(){
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: "+text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println(result);
    }

}
