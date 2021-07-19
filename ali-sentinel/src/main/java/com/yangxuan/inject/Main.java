package com.yangxuan.inject;

import java.util.ArrayList;
import java.util.List;

public class Main {

    List<Injector> mInjectors;

    public Main() {
        mInjectors = new ArrayList<>();
        mInjectors.add(this::print);
    }

    public static void main(String[] args) {
        Main main = new Main();
        for (Injector mInjector : main.mInjectors) {
            mInjector.inject(null);
        }
    }

    public void print(Object o) {
        System.out.println(this.getClass().getName());
        System.out.println("hello world");
    }
}
