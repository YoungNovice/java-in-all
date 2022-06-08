package com.yangxuan.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (Item item : items) {
            bigDecimal =  bigDecimal.add(BigDecimal.valueOf(item.price()));
        }
        return bigDecimal.floatValue();
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }

}
