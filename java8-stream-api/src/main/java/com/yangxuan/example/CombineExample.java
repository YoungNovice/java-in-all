package com.yangxuan.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CombineExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(CombineExample::m1, executors)
                .thenCombine(CompletableFuture.supplyAsync(CombineExample::m2, executors), CombineExample::merge);

        long start = System.nanoTime();
        String s = future.get();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(s);
        System.out.println("Done in " + duration + " msecs");

    }

    public static String merge(String result1, String result2) {
        return result1 + "-" + result2;
    }

    public static String m1() {
        delay();
        return "hello";
    }

    public static String m2() {
        delay();
        return "world";
    }

    public static void delay() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
