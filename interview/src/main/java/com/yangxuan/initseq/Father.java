package com.yangxuan.initseq;

/**
 * 类加载现在加载父类再加载子类 静态代码块和静态变量按书写顺序来
 * (1-before)fc(5)fd(5)(1-after)(sc10)(sd10)(6)
 *
 * 实例化阶段
 * 先实例化子对象再实例化父对象 成员变量和动态代码块按照顺序来
 * (3-before)(fa9)(fb9)(3-after)(2)(sa9)(sb9)(8)(7)
 *
 * 注意，方法重写 father的test实际上调用的是子类的test
 *
 */
public class Father {
    {
        System.out.print("(3-before)");
    }
    static {
        System.out.print("(1-before)");
    }
    private int i1 = test("fa");
    private int i2 = test("fb");

    private static int j1 = method("fc");
    private static int j2 = method("fd");

    Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3-after)");
    }
    static {
        System.out.print("(1-after)");
    }

    public int test(String key) {
        System.out.print(key + "(4)");
        return 1;
    }

    private static int method(String key) {
        System.out.print(key + "(5)");
        return 1;
    }

}
