package com.yangxuan.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChan;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChan = ServerSocketChannel.open();
            listenChan.socket().bind(new InetSocketAddress(PORT));
            listenChan.configureBlocking(false);
            listenChan.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线 " + "total = " + selector.keys().size());
                        }
                        if (key.isReadable()) {
                            readData(key);
                        }
                        iterator.remove();
                    }
                } else {
                    // System.out.println("等待...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from 客户端 " + msg.trim());
                // send
                sendToOtherClients(msg, channel);
            }
            if (count == -1) {
                down(channel, key);
                System.out.println("total = " + selector.keys().size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void down(SocketChannel sc, SelectionKey key) {
        try {
            System.out.println(sc.getRemoteAddress() + "下线了...");
            key.cancel();
            sc.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void sendToOtherClients(String msg, SocketChannel sc) throws IOException {
        System.out.println("服务器消息转发中...");
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != sc) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }
}
