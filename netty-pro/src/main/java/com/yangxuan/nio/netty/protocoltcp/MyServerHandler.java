package com.yangxuan.nio.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                MessageProtocol msg) throws Exception {

        System.out.println("服务器接收到数据如下");
        System.out.println("len=" + msg.getLen());
        System.out.println("内容=" + new String(msg.getContent()));

        System.out.println("count=" + (++count));

        // 回复数据
        byte[] respContent = (UUID.randomUUID() + "\n").getBytes(StandardCharsets.UTF_8);
        int respLen = respContent.length;
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(respLen);
        messageProtocol.setContent(respContent);
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
