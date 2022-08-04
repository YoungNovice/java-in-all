package com.yangxuan.chapter1;

import com.yangxuan.T;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * thenApply——有入参有返回
 * thenApplyAsync——有入参有返回
 * thenAccept——有入参无返回
 * thenAcceptAsync——有入参无返回
 * thenRun——无入参无返回
 * thenRunAsync——无入参无返回
 * thenCombine
 * thenCombineAsync
 * thenCompose
 * thenComposeAsync
 * whenComplete
 * whenCompleteAsync
 * handle
 * handleAsync
 */
public class Stream {

    public static void main(String[] args) throws Exception {
        m3();
    }

    public static void m3() throws Exception {
        ForkJoinPool pool = new ForkJoinPool(4);
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        }, pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        CompletableFuture<Void> cf2 = cf.thenApply((result) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        }).thenAccept((result) -> { //接收上一个任务的执行结果作为入参，但是没有返回值
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(result);
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
        }).thenRun(() -> { //无入参，也没有返回值
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println("thenRun do something");
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        //cf2 等待最后一个thenRun执行完成
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    public static void m2() throws Exception {
        ForkJoinPool pool = new ForkJoinPool(4);
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        }, pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        //thenApply这里实际创建了一个新的CompletableFuture实例
        CompletableFuture<String> cf2 = cf.thenApply((result) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * thenApply   提交的任务类型需遵从Function签名，也就是有入参和返回值，其中入参为前置任务的结果
     * thenAccept  提交的任务类型需遵从Consumer签名，也就是有入参但是没有返回值，其中入参为前置任务的结果
     * thenRun     提交的任务类型需遵从Runnable签名，即没有入参也没有返回值
     */
    public static void m1() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.thenApply((fr1) -> {
            System.out.println("compute 2");
            return fr1 + 10;
        });
        System.out.println("result: " + future2.join());
    }

}
