package com.yangxuan;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestJunit {

    private List<User> list;

    @Before
    public void before() {
        User zhangsan = new User(1L, "zhangsan", 1, 1);
        User lisi = new User(2L, "zhangsan", 2, 1);
        list = Stream.of(zhangsan, lisi).collect(Collectors.toList());
    }

    /**
     * 使用groupingBy 按名称分组
     */
    @Test
    public void groupBy() {
        Map<String, List<User>> groupByName = list.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println(groupByName);
    }

    @Test
    public void objects() {
        if (Objects.nonNull(list)) {
            System.out.println("size is " + list.size());
        } else {
            System.out.println("null");
        }
    }

    @Test
    public void random() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            System.out.println(random.nextInt(10));
        }

        System.out.println("-----------");
        Random random1 = new Random();
        for (int i = 0; i < 5; i++) {
            random1.setSeed(1);
            System.out.println(random1.nextInt(20));
        }
    }

    @Test
    public void initList() {
        ArrayList<User> users = new ArrayList<User>() {{
            add(new User(1L, "zhangsan", 1, 1));
            add(new User(2L, "zhangsan", 2, 1));
        }};
        System.out.println(users);
    }


}
