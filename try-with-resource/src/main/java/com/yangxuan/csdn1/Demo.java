package com.yangxuan.csdn1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws Exception {
        BufferedInputStream bin = null;
        BufferedOutputStream bout = null;
        try {
            bin = new BufferedInputStream(new FileInputStream("test.txt"));
            bout = new BufferedOutputStream(new FileOutputStream("out.txt"));
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    throw e;
                } finally {
                    if (bout != null) {
                        try {
                            bout.close();
                        } catch (IOException e) {
                            throw e;
                        }
                    }
                }
            }
        }
    }

}
