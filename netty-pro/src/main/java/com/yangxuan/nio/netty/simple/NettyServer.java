package com.yangxuan.nio.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 创建BossGroup WorkGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(2);

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workGroup)  // 设置线程组
                    .channel(NioServerSocketChannel.class) // 使用NioSocketChannel作为服务器通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列等待连接数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 保持活动连接状态
                    // workGroup对应的处理器 通道初始化对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 向
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            // 绑定一个端口并且同步 生成一个channelFuture对象
            ChannelFuture cf = bootstrap.bind(6667).sync();
            // 对关闭通道监听
            ChannelFuture cf1 = cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            System.out.println("优雅关闭...");
        }
    }
}
