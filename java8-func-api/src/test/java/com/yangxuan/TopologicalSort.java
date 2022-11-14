package com.yangxuan;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
class Item {
    private String name;
}

@Data
@AllArgsConstructor
class Node {
    private Item item;
    private List<Item> ref;
}

public class TopologicalSort {


    public static void main(String[] args) {
        Item item1 = new Item("12323232");
        Item item2 = new Item("2");
        Item item3 = new Item("3");
        Item item4 = new Item("4");
        Item item5 = new Item("5");
        Item item6 = new Item("6");

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(item1, Lists.newArrayList(item2, item3)));
        nodes.add(new Node(item2, Lists.newArrayList(item5)));
        nodes.add(new Node(item3, Lists.newArrayList(item2, item6)));
        nodes.add(new Node(item4, Lists.newArrayList()));
        nodes.add(new Node(item5, Lists.newArrayList(item6)));
        nodes.add(new Node(item6, Lists.newArrayList()));



    }

}
