package com.yangxuan.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannelSimpleWrite {

    public static void main(String[] args) throws IOException {
        String str = "hello, 杨选";

        String dir = System.getProperty("user.dir");
        FileOutputStream fos = new FileOutputStream(dir + "/a.txt");
        FileChannel channel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        // 写转读
        byteBuffer.flip();

        // 数据写入通道
        channel.write(byteBuffer);
        fos.close();
    }
}
