package com.yangxuan.线程交替输出.toutiao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        BlockingQueue<String> bq1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> bq2 = new ArrayBlockingQueue<>(1);

        new Thread(() -> {
            for (char digital : digitalArr) {
                System.out.println(digital);

                try {
                    bq1.put("ok");
                    bq2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (char letter : letterArr) {
                System.out.println(letter);

                try {
                    bq1.take();
                    bq2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
