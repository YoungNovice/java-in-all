package com.yangxuan.nio.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个channel组
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 连接建立
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        // 将该客户上线的消息发送给其他在线的客户端
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天");
        channelGroup.add(channel);
    }

    // 上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        // 将该客户上线的消息发送给其他在线的客户端
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了");
        // ChannelFutureListener 不需要手动remove
        // channelGroup.remove(channel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(item -> {
            if (item != channel) {
                // 将该客户上线的消息发送给其他在线的客户端
                item.writeAndFlush("[客户]" + channel.remoteAddress() + "发送了消息" + s + "\n");
            } else {
                item.writeAndFlush("[自己]发送了消息" + s + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
