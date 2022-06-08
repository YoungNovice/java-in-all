package com.yangxuan.builder;

import com.yangxuan.builder.pack.Packing;

public interface Item {

    String name();
    Packing packing();
    float price();

}
