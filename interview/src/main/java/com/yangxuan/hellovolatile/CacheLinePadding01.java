package com.yangxuan.hellovolatile;

public class CacheLinePadding01 {

    // long 8字节  cacheline 64字节
    public static volatile long[] arr = new long[16];

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 10_0000_0000L; i++) {
                arr[0] = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 10_0000_0000L; i++) {
                arr[15] = i;
            }
        });

        final long st = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - st) / 100_0000);
    }
}
