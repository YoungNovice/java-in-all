package com.yangxuan;

import com.sun.rmi.rmid.ExecPermission;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 一、消费型接口
 * Consumer<T>
 * void accept(T t)
 *
 * 二、供给型接口
 * Supplier<T>
 * T get()
 *
 * 三、函数式接口
 * Fuction<T,R>
 * R apply(T t)
 *
 * 四、断言型接口
 * Predicate<T>
 * boolean test（T t）
 */
public class LambdaTest2 {

    public void doConsume(long money, int time, Consumer<Long> consumer) {
        consumer.accept(money);
    }

    @Test
    public void consumeTest() {
        Consumer<String> stringConsumer = (s) -> System.out.println("s");
        stringConsumer.accept("hello");
        stringConsumer.accept("nihao");

        doConsume(10, 3,  (l) -> System.out.println(l + "元"));
    }

    @Test
    public void supplierTest() {
        Supplier<String> supplier = () -> "提供string";
        System.out.println(supplier.get());
        System.out.println(supplier.get());
        System.out.println(supplier.get());


        Supplier<Integer> ins = () -> (int)(Math.random()*100);
        System.out.println(ins.get());
        System.out.println(ins.get());
    }

    public String apply(String source, Function<String, String> func) {
        return func.apply(source);
    }

    @Test
    public void function() {
        String source = "ccc";
        String result = apply(source, String::toUpperCase);
        System.out.println(result);
    }

    public boolean test(String student, Predicate<String> predicate) {
        return predicate.test(student);
    }

    @Test
    public void predicateTest() {
        boolean test = test("杨选", (e) -> e.startsWith("杨"));
        System.out.println(test);
    }


}
