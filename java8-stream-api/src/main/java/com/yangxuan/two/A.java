package com.yangxuan.two;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};

        IntStream.of(nums).reduce(0, Integer::sum);

    }
}
