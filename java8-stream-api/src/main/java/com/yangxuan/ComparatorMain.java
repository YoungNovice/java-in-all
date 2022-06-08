package com.yangxuan;

import lombok.SneakyThrows;

import java.awt.*;
import java.util.Comparator;
import java.util.function.Function;

public class ComparatorMain {

    public static void main(String[] args) {
        // comparator1
        Comparator<Point> comparator1 = Comparator.comparingDouble(Point::getX);

        // comparator2
        Comparator<Point> comparator2 = Comparator.comparingDouble(Point::getX);

        Function<Point, Double> keyExtractor = Point::getX;
        Comparator<Double> keyComparator = Double::compare;

        Comparator<Point> comparator3 = (p1, p2) -> keyComparator.compare(keyExtractor.apply(p1), keyExtractor.apply(p2));

        Comparator<Point> comparator4 = (p1, p2) -> keyExtractor.apply(p1).compareTo(keyExtractor.apply(p2));

        Comparator<Point> comparing = Comparator.comparing(Point::getX);


    }

}
