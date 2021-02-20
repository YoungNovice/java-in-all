package com.yangxuan.线程交替输出.toutiao;

public class SynchronizedWaitNotify {

    public static void main(String[] args) {
        final Object obj = new Object();
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (obj) {
                for (char digital : digitalArr) {
                    System.out.println(digital);
                    try {
                        obj.notify();
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (obj) {
                for (char letter : letterArr) {
                    System.out.println(letter);
                    try {
                        obj.notify();
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        }, "t2").start();
    }
}
