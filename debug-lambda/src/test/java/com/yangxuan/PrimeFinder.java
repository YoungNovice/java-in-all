package com.yangxuan;

import javafx.util.Builder;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005
 * 参数解释
 * suspend=n (no)  JVM是否以挂起模式启动 如果以挂起模式启动 则JVM会不执行知道调试器连接到该进程
 * 这个参数在我们想要调试启动过程的时候很有用 (如果没有他我们必须手很快的连接上 不然启动过程就执行完了我们怎么调试)
 */
public class PrimeFinder {

    @Test
    public void intStream() {
        IntStream.iterate(1, n -> n + 1)
                .skip(100)
                .limit(100)
                .filter(PrimeFinder::isPrime)
                .forEach(System.out::println);
    }

    @Test
    public void suppler() {
        useSupplier(Object::new);
    }

    @Test
    public void consumer() {
        Consumer<Object> consumer = (o ) -> {
            System.out.println(o + "haha");
        };

        consumer.accept(new Object());
        // 供给型 -> 消费型
        Supplier<Object> ss = Object::new;
        consumer.accept(ss);
    }

    public void useSupplier(Supplier<Object> s) {
        System.out.println(s.get());
    }

    public static boolean isPrime(int n) {
        int i = 2;
        boolean flag = false;
        for (; i < n; i++) {
            if (n % i == 0) {
                break;
            }
        }
        if (n == i) {
            flag = true;
        }
        return flag;
    }

    @Test
    public void caller() {
        System.out.println("hello");
        System.out.println("world");
        try {
            callee();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public void callee() {
        int a = 1;
        System.out.println("a = " + a);
    }

    @Test
    public void orElseThrow() {
        Optional<Object> optional = Optional.empty();
        System.out.println(optional.orElseThrow(() -> new RuntimeException("nihao")));

        optional.ifPresent((o) -> {
            System.out.println("o" + o);
        });
    }

    public void ifPresent() {
        String name = null;
        Optional.ofNullable(name).ifPresent(System.out::println);
    }

    public void map_optional() {
        Optional<String> java = Optional.of("java");
        String result = java.map(String::toUpperCase).orElse("");
    }

    public void compare() {
        Stream<String> peter = Stream.of("peter", "anna", "mike", "xenia");
        peter.collect(Collectors.toList()).sort(Comparator.reverseOrder());
    }

    class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public void createPerson() {
        PersonFactory<Person> pf = Person::new;
        Person person = pf.create("peter", "joh");
        System.out.println(person);
    }


}
