package com.yangxuan;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Table {}
class Room {}
class House<Q> {}
class Particle<POSITION, MOMENTUM> {}

public class PlayGround {

    public static void main(String[] args) {
        //调用代码及输出
        List<Table> tableList = new ArrayList<Table>();
        Map<Room, Table> maps = new HashMap<Room, Table>();
        House<Room> house = new House<Room>();
        Particle<Long, Double> particle = new Particle<Long, Double>();
        System.out.println(Arrays.toString(tableList.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(maps.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(house.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(particle.getClass().getTypeParameters()));
    }
}
