package com.yangxuan.builder;

public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 26.0f;
    }

    @Override
    public String name() {
        return "ChickenBurger";
    }
}
