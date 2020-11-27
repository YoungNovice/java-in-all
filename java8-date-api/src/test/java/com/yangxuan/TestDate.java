package com.yangxuan;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestDate {

    @Test
    public void now() {
        // 获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println("今天的日期是: " + now);

        // 当前年 月 日
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();

        System.out.println(String.format("年: %s\n月: %s\n日: %s", year, monthValue, dayOfMonth));

    }

    @Test
    public void compare() {
        LocalDate date = LocalDate.of(2016, 4, 13);
        LocalDate today = LocalDate.now();
        System.out.println(String.format("今天的日期是%s吗? %s", date, today.equals(date)));
    }

    @Test
    public void nowTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
    }
}
