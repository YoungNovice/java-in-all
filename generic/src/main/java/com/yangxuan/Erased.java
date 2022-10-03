package com.yangxuan;

public class Erased<T> {
    private final int SIZE = 100;
    public static void f(Object arg) {
        /*
        //编译不通过
        if (arg instanceof T) {
        }
        //编译不通过
        T var = new T();
        //编译不通过
        T[] array = new T[SIZE];
        //编译不通过
        T[] array = (T) new Object[SIZE];
        */
    }
}

