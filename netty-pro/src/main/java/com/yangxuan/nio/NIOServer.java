package com.yangxuan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        // 创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 创建Selector
        Selector selector = Selector.open();
        // 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel注册到selector上 监听事件OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环等待客户端链接
        while (true) {
            if (selector.select(1000) == 0) {
                // System.out.println("等待1秒 无连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 给该客户端生成一个socketchannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 注册到selector上
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("生成一个socketChannel.." + socketChannel.hashCode());
                }

                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    System.out.println("socketChannel可读.." + socketChannel.hashCode());
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    buffer.clear();
                    try {
                        int len = socketChannel.read(buffer);
                        System.out.println("len = " + len);
                        // read
                        System.out.println("from 客户端" + new String(toByteArray(buffer)));
                        if (len == -1) {
                            throw new IOException("读完成");
                        }
                    } catch (IOException e) {
                        key.cancel();
                        socketChannel.close();
                    }
                }
                // 移除SelectionKey
                iterator.remove();
            }
        }
    }

    private static byte[] toByteArray(ByteBuffer buffer) {
        buffer.flip();
        int limit = buffer.limit();
        byte[] bs = new byte[limit];
        int i = 0;
        while (buffer.hasRemaining()) {
            bs[i++] = buffer.get();
        }
        return bs;
    }
}
