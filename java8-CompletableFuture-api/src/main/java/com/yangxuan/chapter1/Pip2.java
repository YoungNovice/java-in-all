package com.yangxuan.chapter1;

import com.yangxuan.T;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Pip2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        m1();
//        m2();
        m3();
    }

    public static void m1() {
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<CompletableFuture<Integer>> future2
                = future1.thenApply(
                (r) -> CompletableFuture.supplyAsync(() -> r + 10));
        System.out.println(future2.join());
        // System.out.println(future2.join().join());
    }

    public static void m2() {
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2
                = future1.thenCompose(
                (r) -> CompletableFuture.supplyAsync(() -> r + 10));
        System.out.println(future2.join());
    }

    public static void m3() throws ExecutionException, InterruptedException {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<String> cf2 = cf.thenCompose((param) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
                T.sleep(2);
                System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
                return "job3 test";
            });
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("cf2 run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }


}
