package com.yangxuan.线程交替输出.toutiao;

import java.util.concurrent.locks.LockSupport;

/**
 * bug 不能自动停掉
 */
public class LockSupportParkUnpark {

    static Thread t1, t2;
    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char digital : digitalArr) {
                System.out.println(digital);
                // 叫醒t2
                LockSupport.unpark(t2);
                // 阻塞t1
                LockSupport.park();
            }
            LockSupport.unpark(t2);
        }, "t1");

        t2 = new Thread(() -> {
            for (char letter : letterArr) {
                // 叫醒t1
                LockSupport.unpark(t1);
                System.out.println(letter);
                // 阻塞t2
                LockSupport.park();
            }
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }
}
