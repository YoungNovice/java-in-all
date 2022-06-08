package com.yangxuan.builder.pack;

import com.yangxuan.builder.pack.Packing;

public class Bottle implements Packing {

    @Override
    public String pack() {
        return "bottle";
    }
}
