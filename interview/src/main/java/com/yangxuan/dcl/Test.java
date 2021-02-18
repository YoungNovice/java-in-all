package com.yangxuan.dcl;

public class Test extends A {

    public Test() {
        a = 1;
        System.out.println("a2 =" + a);
    }

    {
        System.out.println("a3 =" + a);
    }

    public static void main(String[] args) {
        new Test();

    }
}

class A {
    public int a;

    public A() {
        System.out.println("a1 =" + a);
    }

    {
        System.out.println("a0 =" + a);
        a = 2;
    }
}