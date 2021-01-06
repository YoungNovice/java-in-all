package com.yangxuan.juejin6894968780003377165;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/**
 * https://juejin.cn/post/6894968780003377165
 */
public class TimeTest {

    @Test
    public void ofLocalDate() {
        LocalDate.of(2020, 11, 14); //指定年月日
        LocalDate.of(2020, Month.NOVEMBER, 14); //指定年月日 使用Month枚举类
        LocalDate.ofYearDay(2020, 10); //2020年第10天 => 2020-01-10
        LocalDate.now(); //当前时间
        System.out.println(LocalDate.now()); // 比较好的可读性输出 => 2020-11-14
    }

    /**
     * LocalDate常用方法
     *
     */
    @Test
    public void localDate() {
        LocalDate now = LocalDate.of(2020, 11, 14);
        System.out.println(now.getMonth()); //月份的枚举 => NOVEMBER
        System.out.println(now.getMonthValue()); //月份的数字 => 11
        System.out.println(now.getDayOfMonth()); //几号 => 14
        System.out.println(now.getDayOfYear()); // 一年中的第几天 => 319
        System.out.println(now.getDayOfWeek()); // 周几枚举 => SATURDAY
        System.out.println(now.lengthOfMonth()); //本月多少天 => 30
        System.out.println(now.lengthOfYear()); //本年多少天 => 366
    }


    @Test
    public void localTime() {
        LocalTime.of(12, 9, 10); //时、分、秒
        LocalTime.now();
        LocalTime time = LocalTime.of(12, 9, 10);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());


        LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime.of(2020, 11, 14, 13, 10, 50);
        LocalDate.now().atTime(LocalTime.now());
        LocalTime.now().atDate(LocalDate.now());
        LocalDateTime.now();
    }

    @Test
    public void zone() {
        ZoneId.of("Asia/Shanghai");
        ZoneId.systemDefault();
    }

    @Test
    public void zoneDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.of("Asia/Shanghai"));
    }

    @Test
    public void between() {
        Duration between = Duration.between(LocalTime.of(13, 0), LocalTime.of(14, 0));
        between.getSeconds(); //返回两个时间相差的秒数 => 3600

        Period between1 = Period.between(LocalDate.of(2020, 11, 13), LocalDate.of(2020, 11, 13));
        between1.getDays();  //返回相差的天数 => 1

        LocalDate now2 = LocalDate.of(2020, 11, 13);
        System.out.println(now2.plusDays(2));       //加2天   => 2020-11-15
        System.out.println(now2.plusMonths(1));     //加1月   => 2020-12-13
        System.out.println(now2.plusWeeks(1));      //加一周   => 2020-11-20
        System.out.println(now2.minusDays(1));      //减一天   => 2020-11-12
        System.out.println(now2.minusMonths(1));    //减一月   => 2020-10-13
        System.out.println(now2.minusYears(1));     //减一年   => 2019-11-13
        System.out.println(now2.withYear(2021));    //修改年   => 2021-11-13

        LocalDate now3 = LocalDate.of(2020, 11, 13);
        System.out.println(now3.with(TemporalAdjusters.firstDayOfYear())); // 本年的第一天 => 2020-01-01
        System.out.println(now3.with(TemporalAdjusters.next(DayOfWeek.MONDAY))); //下一个周一 => 2020-11-16
        System.out.println(now3.with(TemporalAdjusters.lastDayOfMonth())); // 本月的最后一天 => 2020-11-30
        System.out.println(now3.with(TemporalAdjusters.lastDayOfYear())); // 本年的最后一天 => 2020-12-31

    }

    @Test
    public void customTemporalAdjuster() {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 13, 10, 10, 10);
        System.out.println(localDateTime);
        System.out.println(localDateTime.with((temporal) ->
                temporal.with(ChronoField.SECOND_OF_DAY, 0))); // 当天的凌晨 => 2020-11-13T00:00
        System.out.println(localDateTime.with((temporal) ->
                temporal.with(ChronoField.SECOND_OF_DAY, temporal.range(ChronoField.SECOND_OF_DAY).getMaximum()))); // 当天的最后一刻时间 => 2020-11-13T23:59:59

    }

    @Test
    public void format() {
        System.out.println(LocalDateTime.parse("2020-11-14T20:50:00")); // 输出：2020-11-14T20:50
        System.out.println(LocalDateTime.parse("2020/11/14 20:50:00",
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))); // 输出：2020-11-14T20:50

        LocalDate now4 = LocalDate.of(2020, 11, 13);
        System.out.println(now4.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))); //输出：2020/11/13

        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 11, 13, 10, 10, 10);
        System.out.println(localDateTime2.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))); //输出：2020/11/13 10:10:10

    }






}
