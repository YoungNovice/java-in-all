package com.yangxuan.gen;

public interface MyCons<T extends MyCons<T>> extends Comparable<T> {

    int id();

    String name();

}
