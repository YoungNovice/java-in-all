package com.yangxuan.nio.netty.bounddemo1.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> list) throws Exception {
        System.out.println("MyByteToLongDecoder 被调用");
        if  (in.readableBytes() >= 8) {
            list.add(in.readLong());
        }
    }
}
