package com.yangxuan.nio.netty.unpooled;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Unpooled1 {

    public static void main(String[] args) {

        // readIndex writeIndex capacity
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        System.out.println("capacity = " + buffer.capacity());

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }


    }
}
