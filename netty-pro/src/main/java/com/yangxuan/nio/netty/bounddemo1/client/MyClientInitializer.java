package com.yangxuan.nio.netty.bounddemo1.client;

import com.yangxuan.nio.netty.bounddemo1.server.MyByteToLongDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 出站编码
        pipeline.addLast(new MyLongToByteEncoder());
        // 入站解码
        pipeline.addLast(new MyByteToLongDecoder());

        pipeline.addLast(new MyClientHandler());
    }
}
