package com.yangxuan.线程交替输出.toutiao;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo {

    public static void main(String[] args) throws IOException {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();

        PipedInputStream pis1 = new PipedInputStream();
        PipedInputStream pis2 = new PipedInputStream();
        PipedOutputStream pos1 = new PipedOutputStream();
        PipedOutputStream pos2 = new PipedOutputStream();

        pis1.connect(pos2);
        pis2.connect(pos1);

        String msg = "Your Turn";

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char digital :digitalArr){
                    System.out.println(digital);
                    pos1.write(msg.getBytes());
                    pis1.read(buffer);

                    if (new String(buffer).equals(msg)) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            byte[] buffer = new byte[9];
            try {
                for (char letter :letterArr) {
                    pis2.read(buffer);
                    if (new String(buffer).equals(msg)) {
                        System.out.println(letter);
                    }
                    pos2.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}
