package com.yangxuan.shop;

import java.util.concurrent.CompletableFuture;

public class ShopMain4 {

    public static void main(String[] args) {
        Shop shop = new Shop("a");
        String product = "iphone";
        CompletableFuture<CompletableFuture<Double>> future =
                CompletableFuture.supplyAsync(() -> CompletableFuture.supplyAsync(() -> shop.getPriceDouble(product))
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    return 1;
                }), (price, rate) -> price * rate));


    }
}
