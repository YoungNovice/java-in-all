package com.yangxuan;

import com.google.gson.Gson;

public class QuickStartDemo {

    public static void main(String[] args) {
        Gson gson = new Gson();

        String jsonInteger = gson.toJson(new Integer(1));
        String jsonDouble = gson.toJson(new Double(12345.5432));

        System.out.println("GSON toJson Method Use ");
        System.out.println(jsonInteger);
        System.out.println(jsonDouble);

        Integer javaInteger = gson.fromJson(jsonInteger, Integer.class);
        Double javaDouble = gson.fromJson(jsonDouble, Double.class);


        System.out.println("GSON toJson Method Use ");
        System.out.println(javaInteger);
        System.out.println(javaDouble);

    }
}
