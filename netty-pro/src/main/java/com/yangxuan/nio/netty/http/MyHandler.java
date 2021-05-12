package com.yangxuan.nio.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;

public class MyHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型" + msg.getClass());
            System.out.println("客户端地址 " + LocalDateTime.now() + " -> " + ctx.channel().remoteAddress());

            ByteBuf buf = Unpooled.copiedBuffer("hello， 我是服务器",  CharsetUtil.UTF_8);
            // 构造httpResponse
            DefaultFullHttpResponse resp =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK, buf);
            resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
            resp.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());

            ctx.writeAndFlush(resp);
         }

    }
}
