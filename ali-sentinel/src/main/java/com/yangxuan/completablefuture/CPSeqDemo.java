package com.yangxuan.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CPSeqDemo {

    public static void main(String[] args) throws InterruptedException {
        // 第一个任务
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("A");
        });
        // cfQuery之后成功之后继续执行下一个任务
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });
        // cfFetch之后打印
        cfFetch.thenAccept((result) -> {
            System.out.println("price1: " + result);
        });

        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
