package com.yangxuan.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOFileChannelSimpleRead {

    public static void main(String[] args) throws IOException {

        String dir = System.getProperty("user.dir");
        File file = new File(dir + "/a.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel chan = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        int read = chan.read(buffer);
        buffer.flip();

        byte[] bytes = buffer.array();
        String dest = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(dest);
    }
}
