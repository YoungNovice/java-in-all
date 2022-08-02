package com.yangxuan;

import java.awt.*;
import java.util.Comparator;
import java.util.function.Function;

public class ComparatorMain2 {

    public static void main(String[] args) {

        Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(o1.getX(), o2.getX());
            }
        };

        Comparator<Point> comparator2 = (o1, o2) -> Double.compare(o1.getX(), o2.getX());
        // Comparator<Point> comparator3 = Comparator.comparingDouble(Point::getX);

        // 构建过程
        Function<Point, Double> keyExtractor = Point::getX;
        Comparator<Double> keyComparator = Double::compare;
        Comparator<Point> comparator4 = (d1, d2) -> keyComparator.compare(keyExtractor.apply(d1), keyExtractor.apply(d2));
        // 这仅仅要求keyExtractor提取出的值只需要实现了Comparable接口
        Comparator<Point> comparator5 = (d1, d2) -> keyExtractor.apply(d1).compareTo(keyExtractor.apply(d2));


    }
}
