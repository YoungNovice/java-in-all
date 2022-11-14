package com.yangxuan.csdn1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResource0 {

    public static void main(String[] args) {

        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream("test.txt"));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("out.txt"))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
