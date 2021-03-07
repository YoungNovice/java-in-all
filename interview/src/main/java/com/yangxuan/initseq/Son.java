package com.yangxuan.initseq;

public class Son extends Father {
    private int i1 = test("sa");
    private int i2 = test("sb");
    private static int j1 = method("sc");
    private static int j2 = method("sd");

    static {
        System.out.print("(6)");
    }

    Son() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    @Override
    public int test(String key) {
        System.out.print("(" + key +  "9)");
        return 1;
    }

    private static int method(String key) {
        System.out.print("(" + key +  "10)");
        return 1;
    }

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println();
        Son son1 = new Son();
    }

}
