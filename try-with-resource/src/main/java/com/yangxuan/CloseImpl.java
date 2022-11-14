package com.yangxuan;

public class CloseImpl implements AutoCloseable {

    @Override
    public void close() {
        System.out.println("autoClose");
    }

    public void doSomething() {
        System.out.println("doSomething");
    }

}
