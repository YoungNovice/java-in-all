package com.yangxuan.nio.netty.unpooled;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class Unpooled2 {

    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("hello,world!", CharsetUtil.UTF_8);

        if (buf.hasArray()) {
            byte[] content = buf.array();

            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("buf = " + buf);

            System.out.println(buf.arrayOffset());
            System.out.println(buf.readerIndex());
            System.out.println(buf.writerIndex());
            System.out.println(buf.capacity());

            System.out.println((char) buf.readByte());

            int len = buf.readableBytes();
            System.out.println("len = " + len);

            for (int i = 0; i < len; i++) {
                System.out.println((char) buf.getByte(i));
            }

            System.out.println(buf.getCharSequence(0, 4, CharsetUtil.UTF_8));
            System.out.println(buf.getCharSequence(4, len-4, CharsetUtil.UTF_8));
        }
    }
}
