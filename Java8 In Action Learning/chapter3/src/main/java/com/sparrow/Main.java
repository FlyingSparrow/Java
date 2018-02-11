package com.sparrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.comparing;

/**
 * @author wangjianchun
 * @create 2018/2/10
 */
public class Main {

    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Hello World 1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));

        try {
            String oneLine = processFile((BufferedReader br)-> br.readLine());
            String twoLine = processFile((BufferedReader br) -> br.readLine()+br.readLine());
            System.out.println("oneLine="+oneLine);
            System.out.println("twoLine="+twoLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));

        List<Integer> list = map(Arrays.asList("lambdas", "in", "action"),
                (String s) -> s.length());
        System.out.println(list);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);

        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple apple = c3.apply("green", 110);
        System.out.println(apple);

        sortApples();
        predicateCompose();
        functionCompose();

        integrate();
    }

    private static void integrate() {
        DoubleFunction<Double> f = x -> x+10D;
        Double result = new MathFunction().integrate(f, 3D, 7D);
        System.out.println(result);
    }

    private static void sortApples(){
        List<Apple> inventory = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155), new Apple("red", 120));

        inventory.sort(new AppleComparator());

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        Comparator<Apple> comparator = comparing((Apple a) -> a.getWeight());

        inventory.sort(comparing(a -> a.getWeight()));

        inventory.sort(comparing(Apple::getWeight));

        inventory.sort(comparing(Apple::getWeight).reversed());

        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));
    }

    private static void predicateCompose(){
        Predicate<Apple> redApple = (apple) -> "red".equals(apple.getColor());
        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
                .or(apple -> "green".equals(apple.getColor()));
    }

    private static void functionCompose(){
        Function<Integer, Integer> f = x -> x+1;
        Function<Integer, Integer> g = x -> x*2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println("result="+result);

        Function<Integer, Integer> h2 = f.compose(g);
        int result2 = h2.apply(1);
        System.out.println("result2="+result2);

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        Function<String, String> transformationPipeline2 = addHeader.andThen(Letter::addFooter);
    }

    private static void process(Runnable runnable){
        runnable.run();
    }

    private static String processFile(BufferedReaderProcessor processor) throws IOException{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(
                Main.class.getClassLoader().getResourceAsStream("data.txt")
        ))){
            return processor.process(br);
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for(T s : list){
            if(p.test(s)){
                results.add(s);
            }
        }
        return results;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i : list){
            c.accept(i);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for(T s : list){
            result.add(f.apply(s));
        }
        return result;
    }
}
