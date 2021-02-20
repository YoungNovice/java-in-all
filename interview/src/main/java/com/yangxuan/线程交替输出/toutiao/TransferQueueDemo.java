package com.yangxuan.线程交替输出.toutiao;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueDemo {

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        TransferQueue<Character> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                for (char digital : digitalArr) {
                    queue.transfer(digital);
                    System.out.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                for (char letter : letterArr) {
                    System.out.println(queue.take());
                    queue.transfer(letter);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
