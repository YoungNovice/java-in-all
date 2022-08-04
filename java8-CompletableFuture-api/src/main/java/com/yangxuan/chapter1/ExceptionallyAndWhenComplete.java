package com.yangxuan.chapter1;

import com.yangxuan.T;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;


public class ExceptionallyAndWhenComplete {

    public static void main(String[] args) throws Exception {
        m5();
    }

    public static void m5() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.handle((r, e) -> {
            if (e != null) {
                System.out.println("compute failed!");
                return r;
            } else {
                System.out.println("received result is " + r);
                return r + 10;
            }
        });
        System.out.println("result: " + future2.join());
    }

    public static void m4() throws Exception {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("1");
        });
        CompletableFuture<Integer> cfe = cf1.exceptionally((e) -> 1);
        cfe.whenComplete((i, e) -> {
            System.out.println(i);
        });

        // cf1.join();
        cfe.join();
    }

    public static void m3() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            T.sleep(2);
            if (true) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        });

        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<Double> cf2 = cf.whenComplete((a, b) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            T.sleep(2);
            if (b != null) {
                System.out.println("error stack trace->");
                // b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
        });

        //等待子任务执行完成
        System.out.println("main thread start wait,time->" + System.currentTimeMillis());
        //如果cf是正常执行的，cf2.get的结果就是cf执行的结果
        //如果cf是执行异常，则cf2.get会抛出异常
        System.out.println("run result->" + cf2.join());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    public static void m2() {
        CompletableFuture<Integer> future1
                = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });
        CompletableFuture<Integer> future2 = future1.whenComplete((r, e) -> {
            if (e != null) {
                System.out.println("compute failed!");
            } else {
                System.out.println("received result is " + r);
            }
        });
        // System.out.println("result: " + future1.join());
    }


    /**
     * exceptionally方法指定某个任务执行异常时执行的回调方法，会将抛出异常作为参数传递到回调方法中，
     * 如果该任务正常执行则会exceptionally方法返回的CompletionStage的result就是该任务正常执行的结果
     */
    public static void m1() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            T.sleep(2);
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        }, pool);
        //cf执行异常时，将抛出的异常作为入参传递给回调方法
        CompletableFuture<Double> cf2 = cf.exceptionally((param) -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println("error stack trace->");
            // param.printStackTrace();
            System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
            return -1.1;
        });
        //cf正常执行时执行的逻辑，如果执行异常则不调用此逻辑
        CompletableFuture<Void> cf3 = cf.thenAccept((param) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            T.sleep(2);
            System.out.println("param->" + param);
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
        });
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成,此处无论是job2和job3都可以实现job2退出，主线程才退出，如果是cf，则主线程不会等待job2执行完成自动退出了
        //cf2.get时，没有异常，但是依然有返回值，就是cf的返回值
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }
}
