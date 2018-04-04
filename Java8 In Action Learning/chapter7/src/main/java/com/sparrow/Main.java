package com.sparrow;

import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author wangjianchun
 * @create 2018/2/26
 */
public class Main {

    public static void main(String[] args) {
        long number = 1_000_000;
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreams::sequentialSum, number) + " msecs");
        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStreams::iterativeSum, number) + " msecs");
        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreams::parallelSum, number) + " msecs");
        System.out.println("Ranged sum done in: " + measureSumPerf(ParallelStreams::rangedSum, number) + " msecs");
        System.out.println("Parallel range sum done in: " + measureSumPerf(ParallelStreams::parallelRangedSum, number) + " msecs");
        System.out.println("SideEfffect parallel sum done in: " + measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs");
        System.out.println("FokkJoin sum done in: "+measureSumPerf(ForkJoinSumCalculator::forkJoinSum, number)+" msecs");
        System.out.println(Runtime.getRuntime().availableProcessors());


        final String SENTENCE = " Nel mezzo del cammin di nostra vita "
                + "mi ritrovai in una selva oscura"
                + " ché la dritta via era smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found "+countWords(stream)+" words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> characterStream = StreamSupport.stream(spliterator, true);
        System.out.println("Found "+countWords(characterStream)+" words");
    }

    private static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    private static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static int countWordsIteratively(String string){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : string.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace){
                    counter++;
                }
                lastSpace = false;
            }
        }

        return counter;
    }

}
