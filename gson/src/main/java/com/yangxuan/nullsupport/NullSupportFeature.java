package com.yangxuan.nullsupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yangxuan.Student;

public class NullSupportFeature {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        Student aStudent = new Student();
        aStudent.setName("Sandeep Kumar Patel");
        aStudent.setSubject(null);
        aStudent.setMark(234);

        String studentJson = gson.toJson(aStudent);

        System.out.println(studentJson);

        Student javaStudentObject = gson.fromJson(studentJson, Student.class);

        System.out.println("Student Subject: " + javaStudentObject.getSubject());
        System.out.println("Student Name: " + javaStudentObject.getName());
    }
}