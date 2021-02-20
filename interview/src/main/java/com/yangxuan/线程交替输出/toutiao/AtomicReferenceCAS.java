package com.yangxuan.线程交替输出.toutiao;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceCAS {

    private static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        AtomicReference<Thread> atomicReference = new AtomicReference<>();

        t1 = new Thread(() -> {
            for (char digital : digitalArr) {
                while (atomicReference.get() != t1) {} // cas 自旋
                System.out.println(digital);
                atomicReference.set(t2);
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char letter : letterArr) {
                while (atomicReference.get() != t2) {} // cas
                System.out.println(letter);
                atomicReference.set(t1);
            }
        }, "t2");

        atomicReference.set(t1);

        t1.start();
        t2.start();
    }
}
