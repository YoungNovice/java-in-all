package com.yangxuan.chapter1;

import com.yangxuan.T;

import java.util.concurrent.CompletableFuture;

public class Basic {

    public static void main(String[] args) {
        m3();
    }

    public static void m3() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("compute test");
        });
        System.out.println("get result: " + future.join());
    }

    public static void m2() {
        CompletableFuture<String> future
                = CompletableFuture.supplyAsync(() -> {
            T.sleep(3);
            System.out.println("compute test");
            return "test";
        });

        T.wrap(() -> {
            T.sleep(3);
            String result = future.join();
            System.out.println("get result: " + result);
        });
    }

    public static void m1() {
        CompletableFuture<String> future = new CompletableFuture<>();

        // 在另外一个线程中完成
        CompletableFuture.runAsync(() -> {
            T.sleep(5);
            future.complete("123");
        });

        T.wrap(() -> {
            String result = future.join();
            System.out.println(result);
        });
    }
}
