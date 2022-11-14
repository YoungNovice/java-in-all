package com.yangxuan.java7_java9_diff;

import com.yangxuan.CloseImpl;

import java.io.IOException;

public class Java7 {

    public static void main(String[] args) throws IOException {
        ok1();
        ok2();
    }

    static void ok1() {
        CloseImpl close = new CloseImpl();
        try (CloseImpl close1 = close) {
            close1.doSomething();
        }
    }

    static void ok2() {
        try (CloseImpl close = new CloseImpl()) {
            close.doSomething();
        }
    }

    static void no() {
        CloseImpl close = new CloseImpl();
        // java7不行 java9可以
        /*try (close) {
            close.doSomething();
        }*/
    }

}
