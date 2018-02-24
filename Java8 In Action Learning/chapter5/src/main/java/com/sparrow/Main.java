package com.sparrow;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wangjianchun
 * @create 2018/2/24
 */
public class Main {

    private static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    private static final Trader raoul = new Trader("Raoul", "Cambridge");
    private static final Trader mario = new Trader("Mario", "Milan");
    private static final Trader alan = new Trader("Alan", "Cambridge");
    private static final Trader brian = new Trader("Brian", "Cambridge");

    private static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
        flatMap();
        match();
        find();
        reduce();
        System.out.println("============================");
        transaction1();
        transaction2();
        transaction3();
        transaction4();
        transaction5();
        transaction6();
        transaction7();
        transaction8();
        System.out.println("============================");
        buildStream();
    }

    private static void flatMap(){
        List<String> words = Arrays.asList("Hello", "World");

        List<String> uniqueCharacters = words.stream().map(word -> word.split(""))
            .flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        System.out.println("uniqueCharacters: "+uniqueCharacters);

        List<Integer> numbers = Arrays.asList(1,4,9,16,25);
        List<Integer> squares = numbers.stream().map(number -> number*number)
                .collect(Collectors.toList());
        System.out.println("squares: "+squares);

        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream()
                .filter(j -> (i+j)%3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.stream().forEach(pair ->{
            System.out.println("("+pair[0]+","+pair[1]+")");
        });
    }

    private static void match(){
        if(menu.stream().anyMatch(dish -> dish.isVegetarian())){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("isHealthy: "+isHealthy);
    }

    private static void find(){
        Optional<Dish> dish = menu.stream().filter(dish1 -> dish1.isVegetarian())
                .findAny();
        System.out.println(dish.get());
        dish.ifPresent(dish1 -> System.out.println(dish1.getName()));

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree = numbers.stream().map(x -> x*x)
                .filter(x -> x%3==0).findFirst();
        firstSquareDivisibleByThree.ifPresent(number -> System.out.println(number));
    }

    private static void reduce(){
        List<Integer> numbers = Arrays.asList(4,5,3,9);
        int sum = numbers.stream().reduce(0,(a,b)->a+b);
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum: "+sum+", sum2: "+sum2);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println("max: "+max);

        int count = menu.stream().map(dish1 -> 1).reduce(0, (a,b)->a+b);
        System.out.println("menu's count: "+count);
    }

    private static void transaction1(){
        List<Transaction> transactionList = transactions.stream().filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println("transactionList: "+transactionList);
    }

    private static void transaction2(){
        List<String> cityList = transactions.stream().map(Transaction::getTrader).map(Trader::getCity)
                .distinct().collect(Collectors.toList());
        System.out.println("cityList: "+cityList);
    }

    private static void transaction3(){
        List<Trader> traderList = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct().sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("traderList: "+traderList);
    }

    private static void transaction4(){
        List<String> traderNameList = transactions.stream().map(Transaction::getTrader).map(Trader::getName)
                .distinct().sorted().collect(Collectors.toList());
        System.out.println("traderNameList: "+traderNameList);
    }

    private static void transaction5(){
        boolean flag = transactions.stream().map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println("The result is "+flag+" that if there are traders work in Milan");
    }

    private static void transaction6(){
        transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue).forEach(System.out::println);
    }

    private static void transaction7(){
        Optional<Integer> integerOptional = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        integerOptional.ifPresent(maxTransactionValue->System.out.println("maxTransactionValue: "+maxTransactionValue));
    }

    private static void transaction8(){
        int minTransactionValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min).get();
        System.out.println("minTransactionValue: "+minTransactionValue);
    }

    private static void buildStream(){
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a*a + b*b)% 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)}));

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2]%1 == 0));

        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));
        pythagoreanTriples2.limit(5).forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        int[] numbers = {2,3,5,7,11,13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum: "+sum);

        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct().count();
            System.out.println("uniqueWords: "+uniqueWords);
        }catch (IOException e){
            e.printStackTrace();
        }

        Stream.iterate(0, n -> n+2).limit(10).forEach(System.out::println);
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0]+t[1]}).limit(20)
                .forEach(t->System.out.println("("+t[0]+", "+t[1]+")"));

        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous+this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }


}
