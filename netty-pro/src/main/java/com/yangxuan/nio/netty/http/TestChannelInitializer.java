package com.yangxuan.nio.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        // http解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        pipeline.addLast("MyHandler", new MyHandler());
    }
}
