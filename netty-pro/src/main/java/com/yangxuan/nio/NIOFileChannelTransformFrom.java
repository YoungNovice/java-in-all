package com.yangxuan.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOFileChannelTransformFrom {

    public static void main(String[] args) throws IOException {
        String src = "a.txt";
        String dest = "c.txt";

        String dir = System.getProperty("user.dir");
        File srcFile = new File(dir + "/" + src);
        File destFile = new File(dir + "/" + dest);

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        FileChannel fisChannel = fis.getChannel();
        FileChannel descChannel = fos.getChannel();

        // read 从通道读取数据并放到缓冲区
        // write 把缓冲区中的数据写入通道
        // transferFrom 从目标通道复制数据数据到当前通道
        // transferTo 把数据从当前通道复制目标通道

        // 1. descChannel.transferFrom(fisChannel, 0, fisChannel.size());
        // 2. fisChannel.transferTo(0, fisChannel.size(), descChannel);
        fis.close();
        fos.close();
    }
}
