package com.yangxuan.shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

public class ShopMain2 {

    List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))))
                        .collect(toList());


        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });

    public static void main(String[] args) {
        ShopMain2 main = new ShopMain2();
        long start = System.nanoTime();
        System.out.println(main.findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");


    }

}
