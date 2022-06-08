package com.yangxuan.builder;

import com.yangxuan.builder.pack.Bottle;
import com.yangxuan.builder.pack.Packing;

public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

}
