package com.yangxuan.nio.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("client is ok");
            // sync让方法不会阻塞在这里
            ChannelFuture cf = bootstrap.connect("127.0.0.1", 6667).sync();
            // 给关闭通道
            ChannelFuture cf1 = cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
