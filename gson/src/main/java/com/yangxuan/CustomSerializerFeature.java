package com.yangxuan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomSerializerFeature {

    public static void main(String[] args) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Student.class, new StudentTypeSerializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        Student student = new Student();
        student.setName("Sandeep");
        student.setMark(150);
        student.setSubject("Arithmetic");

        String studentJson = gson.toJson(student);
        System.out.println("Custom Serializer : Json String Representation ");
        System.out.println(studentJson);

        gsonBuilder.registerTypeAdapter(Student.class, new StudentTypeDeserializer());
        Gson gsonde = gsonBuilder.create();
        Student student1 = gsonde.fromJson(studentJson, Student.class);
        System.out.println(student1);

    }
}
