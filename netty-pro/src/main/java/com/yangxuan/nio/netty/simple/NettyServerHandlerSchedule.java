package com.yangxuan.nio.netty.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandlerSchedule extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        System.out.println("当前线程 " + Thread.currentThread().getName());

        ctx.channel().eventLoop().schedule(() -> {
            System.out.println("server ctx = " + ctx);
            System.out.println("当前线程 " + Thread.currentThread().getName());
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端22～", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);

        ctx.channel().eventLoop().schedule(() -> {
            System.out.println("server ctx = " + ctx);
            System.out.println("当前线程 " + Thread.currentThread().getName());
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端33～", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端11～", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
