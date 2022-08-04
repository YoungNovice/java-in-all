package com.yangxuan;

import java.util.concurrent.TimeUnit;

public final class T {

    public static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wrap(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }


}
