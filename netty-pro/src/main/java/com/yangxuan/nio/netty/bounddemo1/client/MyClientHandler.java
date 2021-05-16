package com.yangxuan.nio.netty.bounddemo1.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("从服务端" + ctx.channel().remoteAddress() + " 读取到long " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        // 这个会调用 MyLongToByteEncoder因为我们传递的是Long
        ctx.writeAndFlush(123456L);

        // 这个不会调用 MyLongToByteEncoder
        // io.netty.handler.codec.MessageToByteEncoder.write这个方法会校验类型是否支持
        // ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
