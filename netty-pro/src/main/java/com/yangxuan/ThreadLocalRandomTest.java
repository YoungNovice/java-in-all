package com.yangxuan;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (isInterested(random.nextInt(10))) {
                count++;
            }
        }
        System.out.printf("Found %d interested values%n", count);
    }



    private static boolean isInterested(int i) {
        return i % 2 == 0;
    }
}
