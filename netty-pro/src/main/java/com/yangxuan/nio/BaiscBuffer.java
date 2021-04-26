package com.yangxuan.nio;

import javax.sound.midi.Soundbank;
import java.nio.IntBuffer;

public class BaiscBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 向buffer中写数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(10  * i);
        }
        // 将buffer读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

}
