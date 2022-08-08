package com.yangxuan.chapter2;

import com.yangxuan.T;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor1 = Executors.newFixedThreadPool(4);
        ExecutorService executor2 = Executors.newFixedThreadPool(4);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("x thread" + Thread.currentThread());
            T.sleep(2);
            return "first";
        }, executor1);
        CompletableFuture<String> f2 = future.thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("y thread" + Thread.currentThread());
            T.sleep(1);
            return 222;
        }, executor2), Main::merge);
        // 在没有指定线程池的情况下merge的执行是交给最后一个线程执行的
        // executor1 executor2 main三者都有可能
        System.out.println(f2.join());
    }

    public static String merge(String a, int b) {
        System.out.println("z thread" + Thread.currentThread());
        return a + b;
    }

}
