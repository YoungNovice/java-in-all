package com.yangxuan.builder;

import com.yangxuan.builder.pack.Packing;
import com.yangxuan.builder.pack.Wrapper;

public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}
