package com.yangxuan.线程交替输出.toutiao;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCAS {

    private static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char digital : digitalArr) {
                while (threadNo.get() != 1) {} // cas 自旋
                System.out.println(digital);
                threadNo.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char letter : letterArr) {
                while (threadNo.get() != 2) {} // cas
                System.out.println(letter);
                threadNo.set(1);
            }
        }, "t2").start();

    }
}
