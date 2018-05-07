package com.sparrow.chapter14;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class MyMathUtils {

    public static Stream<Integer> primes(int n){
        return Stream.iterate(2, i -> i+1).filter(MyMathUtils::isPrime).limit(n);
    }

    public static boolean isPrime(int candidate){
        int candidateRoot = (int)Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate%i == 0);
    }

    public static LazyList<Integer> from(int n){
        return new LazyList<Integer>(n, () -> from(n+1));
    }

    public static LazyList<Integer> primes(LazyList<Integer> numbers){
        return new LazyList<Integer>(numbers.head(), () -> primes(numbers.tail().filter(n -> n%numbers.head() != 0)));
    }

    public static <T> void printAll(MyList<T> list){
//        while (!list.isEmpty()){
//            System.out.println(list.head());
//            list = list.tail();
//        }

        if(list.isEmpty()){
            return;
        }
        System.out.println(list.head());
        printAll(list.tail());
    }
}
