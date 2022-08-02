package com.yangxuan.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComposeExample {

    static ExecutorService executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(ComposeExample::m1, executors)
                .thenCompose(ComposeExample::cm2);

        long start = System.nanoTime();
        String s = future.get();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(s);
        System.out.println("Done in " + duration + " msecs");

    }


    public static String m1() {
        delay();
        return "hello";
    }

    public static String m2(String first) {
        delay();
        return first + "world";
    }

    public static CompletableFuture<String> cm2(String first) {
        return CompletableFuture.supplyAsync(() -> ComposeExample.m2(first), executors);
    }

    public static void delay() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
