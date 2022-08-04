package com.yangxuan;

import java.util.ArrayList;

/**
 * -XX:+HeapDumpOnOutOfMemoryError -Xmx20m -Xms20m -XX:+PrintGCDetails
 *
 */
public class Demo2 {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        System.out.println(1);
        while (true) {
            list.add(new Demo2());
        }
    }
}
