package com.yangxuan.shop;

import java.util.concurrent.Future;

public class ShopMain {

    public static void main(String[] args) {
        call0();

    }

    public static void call2() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // 执行更多任务，比如查询其他商店
        // doSomethingElse();
        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static void call1() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync1("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // 执行更多任务，比如查询其他商店
        // doSomethingElse();
        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public static void call0() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync0("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // 执行更多任务，比如查询其他商店
        // doSomethingElse();
        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
