package com.yangxuan.nio.netty.tcp;

import com.yangxuan.nio.netty.bounddemo1.client.MyLongToByteEncoder;
import com.yangxuan.nio.netty.bounddemo1.server.MyByteToLongDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new MyClientHandler());
    }
}
