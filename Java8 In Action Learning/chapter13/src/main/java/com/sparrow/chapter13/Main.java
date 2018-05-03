package com.sparrow.chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

/**
 * @author wangjianchun
 * @create 2018/5/4
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 9, 17, 30);

        List<List<Integer>> subsets = subsets(list);
        for(List<Integer> item : subsets){
            System.out.println(item);
        }

        System.out.println(factorialIterative(10));
        System.out.println(factorialRecursive(10));
        System.out.println(factorialStreams(10));
    }

    private static List<List<Integer>> subsets(List<Integer> list){
        if(list.isEmpty()){
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            ans.add(Collections.<Integer>emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);

        return concat(subans, subans2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> subans, List<List<Integer>> subans2) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(subans);
        result.addAll(subans2);

        return result;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(List<Integer> item : subans){
            List<Integer> subList = new ArrayList<>(item);
            subList.add(0, first);
            result.add(subList);
        }
        return result;
    }

    private static int factorialIterative(int n){
        int result = 1;
        for(int i=1; i<=n; i++){
            result *= i;
        }
        return result;
    }

    private static int factorialRecursive(int n){
//        return n == 1?1: n*factorialRecursive(n-1);
        //基于尾部递归的阶乘实现
        return factorialHelper(1, n);
    }

    private static int factorialHelper(int acc, int n){
        return n == 1? acc: factorialHelper(acc*n, n-1);
    }

    private static long factorialStreams(long n){
        return LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a*b);
    }
}
