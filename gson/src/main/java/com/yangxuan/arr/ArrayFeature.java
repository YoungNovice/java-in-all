package com.yangxuan.arr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ArrayFeature {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().create();

        int[] numberArray = { 121, 23, 34, 44, 52 };

        String[] fruitsArray = { "apple", "oranges", "grapes" };

        String jsonNumber = gson.toJson(numberArray);
        String jsonString = gson.toJson(fruitsArray);

        System.out.println(jsonNumber);
        System.out.println(jsonString);

        int[] numCollectionArray = gson.fromJson(jsonNumber, int[].class);
        String[] fruitBasketArray = gson.fromJson(jsonString, String[].class);

        System.out.println("Number Array Length " + numCollectionArray.length);
        System.out.println("Fruit Array Length " + fruitBasketArray.length);

    }

}