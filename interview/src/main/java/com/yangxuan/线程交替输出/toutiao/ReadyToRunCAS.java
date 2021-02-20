package com.yangxuan.线程交替输出.toutiao;

enum  ReadyToRunEnum {
    T1, T2
}

public class ReadyToRunCAS {

    private static volatile ReadyToRunEnum READY_TO_RUN = ReadyToRunEnum.T1;

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char digital : digitalArr) {
                while (READY_TO_RUN != ReadyToRunEnum.T1) {} // cas 自旋
                System.out.println(digital);
                READY_TO_RUN = ReadyToRunEnum.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char letter : letterArr) {
                while (READY_TO_RUN != ReadyToRunEnum.T2) {} // cas
                System.out.println(letter);
                READY_TO_RUN = ReadyToRunEnum.T1;
            }
        }, "t2").start();
    }
}


