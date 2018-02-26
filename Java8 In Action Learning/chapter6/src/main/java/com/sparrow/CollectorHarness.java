package com.sparrow;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author wangjianchun
 * @create 2018/2/26
 */
public class CollectorHarness {

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for(int i=0; i<10; i++){
            long start = System.nanoTime();
//            partitionPrimes(1_000_000);
            partitionPrimesWithCustomeCollector(1_000_000);
            long duration = (System.nanoTime()-start)/1_000_000;
            if(duration < fastest){
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in "+fastest+" msecs");
    }


    private static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    private static Map<Boolean, List<Integer>> partitionPrimesWithCustomeCollector(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    /**
     * 判断 candidate 是否是质数
     * @param candidate
     * @return
     */
    private static boolean isPrime(int candidate){
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate%i == 0);
    }

}
