package com.yangxuan;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestOptional {

    @Test
    public void OfNullable() {
        Person person = new Person();
        person.setName("你好");
        person.setAge(26);

        Person person1 = Optional.of(person).orElse(new Person());
        System.out.println(person1);

        Optional<Person> op = Optional.of(person);
        op.ifPresent(p -> System.out.println("年龄" + p.getAge()));
    }

    // Function Consumer Supplier
    @Test
    public void filter() {
        List<Person> list = Stream.of(new Person("a", 1), new Person("b", 2)).collect(Collectors.toList());
        Person person = new Person("name1", 1);
        Optional<Person> o1 = Optional.ofNullable(person).filter(p -> p.getAge() > 20);

        String name = Optional.of(person).map(Person::getName).orElse("你好");
        System.out.println(name);
    }

    @Test
    public void supplier() {
        Supplier<Person> supplier = Person::new;
        Person person = supplier.get();
    }

    /**
     * 为空抛异常
     */
    @Test
    public void orElseThrow() {
        Optional.ofNullable(null).orElseThrow(() -> new RuntimeException("有问题"));
    }
}
