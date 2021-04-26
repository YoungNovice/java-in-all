package com.yangxuan.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannelSimpleCopy {

    public static void main(String[] args) throws IOException {
        String src = "a.txt";
        String dest = "b.txt";

        String dir = System.getProperty("user.dir");
        File srcFile = new File(dir + "/" + src);
        File destFile = new File(dir + "/" + dest);

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        int len = -1;
        while ((len = fisChannel.read(buffer)) > 0) {
            System.out.println("len = " + len);
            buffer.flip();
            fosChannel.write(buffer);
            buffer.flip();
            // or buffer.clear();
        }

        fis.close();
        fos.close();
    }
}
