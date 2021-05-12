package com.yangxuan.nio.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandlerExecute extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        System.out.println("当前线程 " + Thread.currentThread().getName());

        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("server ctx = " + ctx);
            System.out.println("当前线程 " + Thread.currentThread().getName());
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端2～", CharsetUtil.UTF_8));
        });

        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("server ctx = " + ctx);
            System.out.println("当前线程 " + Thread.currentThread().getName());
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端3～", CharsetUtil.UTF_8));
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端1～", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
