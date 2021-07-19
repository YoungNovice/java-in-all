package com.yangxuan.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CPDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CPDemo1::fetchPrice);
        // 如果执行成功
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻关闭 否则CompletableFuture默认使用的线程池会立刻关闭
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed");
        }

        return 5 + Math.random() * 20;
    }
}
