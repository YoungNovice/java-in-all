package com.yangxuan;

import java.util.ArrayList;
import java.util.List;

public class GcDemo {

    /**
     * vm options -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
     *
     * @param args .
     */
    public static void main(String[] args) {
        testOutOfMemory();
    }

    public static void testOutOfMemory() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            String str = new String();
            list.add(str);
        }
    }
}
