package com.yangxuan;

import java.util.List;


class A {
}

class B extends A {
}


public class GenericsDemo25 {


    // 如下两个方法不会报错
    public static void funA(A a) {
        // ...
    }

    public static void funB(B b) {
        funA(b);
        // ...
    }

    // 如下funD方法会报错
    public static void funC(List<A> listA) {
        // ...
    }

    public static void funD(List<B> listB) {
        // funC(listB); // Unresolved compilation problem: The method doPrint(List<A>) in the type test is not applicable for the arguments (List<B>)
        // ...
    }
    //为了解决泛型中隐含的转换问题，Java泛型加入了类型参数的上下边界机制。<? extends A>表示该类型参数可以是A(上边界)或者A的子类类型。编译时擦除到类型A，即用A类型代替类型参数。这种方法可以解决开始遇到的问题，编译器知道类型参数的范围，如果传入的实例类型B是在这个范围内的话允许转换，这时只要一次类型转换就可以了，运行时会把对象当做A的实例看待。

    //解决方案如下
    public static <T extends A> void funE(List<T> listA) {
        // ...
    }

    public static void funF(List<B> listB) {
        funE(listB); // OK
        // ...
    }

}
