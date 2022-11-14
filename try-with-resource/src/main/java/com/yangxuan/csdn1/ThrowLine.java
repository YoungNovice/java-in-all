package com.yangxuan.csdn1;

public class ThrowLine {

    public static void main(String[] args) {
        // finally 如果有return 里面正常返回了 throw e无法抛出
        // System.out.println(test1());

        // throw e 可以执行
        test2();
    }

    public static void test2() {
        Object o = null;
        try {
            o.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("finally");
        }
    }

    public static String test1() {
        Object o = null;
        try {
            o.toString();
            return "try";
        } catch (Exception e) {
            throw e;
        } finally {
            return "finally";
        }
    }
}
