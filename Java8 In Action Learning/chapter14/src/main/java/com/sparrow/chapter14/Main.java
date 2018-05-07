package com.sparrow.chapter14;

import java.util.function.Function;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class Main {

    public static void main(String[] args) {
        MyList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
        lazyListDemo();

        //输出无限多的质数
//        MyMathUtils.printAll(MyMathUtils.primes(MyMathUtils.from(2)));

        System.out.println(repeat(3, (Integer x) -> 2*x).apply(10));
    }

    private static void lazyListDemo(){
        LazyList<Integer> numbers = MyMathUtils.from(2);
        int two = MyMathUtils.primes(numbers).head();
        int three = MyMathUtils.primes(numbers).tail().head();
        int four = MyMathUtils.primes(numbers).tail().tail().head();
        System.out.println(two+" "+three+" "+four);
    }

    private static <A, B, C> Function<A, C> compose(Function<B,C> g, Function<A, B> f){
        return x -> g.apply(f.apply(x));
    }

    private static <A> Function<A, A> repeat(int n, Function<A, A> function){
        return n == 0 ? x -> x : compose(function, repeat(n-1, function));
    }
}
