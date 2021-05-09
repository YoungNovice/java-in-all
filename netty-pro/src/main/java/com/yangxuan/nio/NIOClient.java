package com.yangxuan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中。。。");
            }
        }

        String str = "hello world";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据
        socketChannel.write(buffer);
        System.in.read();
    }
}
