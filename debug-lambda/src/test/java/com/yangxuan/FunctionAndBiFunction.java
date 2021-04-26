package com.yangxuan;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionAndBiFunction {

    public static void main(String[] args) {
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        int value2 = name.compose(square).apply(3);
        Function<Integer, Integer> compose = name.compose(square);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);



    }

    // Function接口如果引用的是静态方法 那么apply根方法参数一样
    // 如果引用的是实例方法 那么apply的参数是实例 返回值是实例方法调用的返回值
    @Test
    public void f() {
//        Function<String, String> f = String::toUpperCase;
        Function<String, String> f = FunctionAndBiFunction::a;
        String out = f.apply("sdf");
        System.out.println(out);

        // 从下面四个例子可以看出 引用静态方法， 参数保持一致
        // 而引用实例方法 第一个参数是实例， 后面的保持一致
        // Function<A, Integer> f2 = FunctionAndBiFunction.A::doSomething;
        // Function<A, Integer> f2_static = FunctionAndBiFunction.A::doStaticSomething;

        // BiFunction<A, Integer, Integer> f3 = FunctionAndBiFunction.A::doSomething;
        // BiFunction<A, Integer, Integer> f3_static = FunctionAndBiFunction.A::doStaticSomething;
    }

    private static String a(String b) {
        return b + "aaa";
    }

    static class A {

        public int doSomething() {
            return 1;
        }

        public static int doStaticSomething(A x) {
            return 1;
        }

        public int doSomething(int x) {
            return 1;
        }

        public static int doStaticSomething(A a, int x) {
            return 1;
        }

    }


}
