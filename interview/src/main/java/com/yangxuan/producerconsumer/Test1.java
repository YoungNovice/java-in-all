package com.yangxuan.producerconsumer;

import java.util.concurrent.TimeUnit;

public class Test1 {

    public static void main(String[] args) {
        //PC pc = new PC();
        //PC1 pc = new PC1();
        PC2 pc = new PC2();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        pc.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        pc.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
