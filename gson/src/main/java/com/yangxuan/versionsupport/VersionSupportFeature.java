package com.yangxuan.versionsupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;

@Since(1.0)
class Student {

    private String name;

    private String subject;

    private int mark;

    @Since(1.1)
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}

public class VersionSupportFeature {

    public static void main(String[] args) {

        Student aStudent = new Student();
        aStudent.setName("Sandeep Kumar Patel");
        aStudent.setSubject("Algebra");
        aStudent.setMark(534);
        aStudent.setGender("Male");

        System.out.println("Student json for Version 1.0 ");
        Gson gson = new GsonBuilder().setVersion(1.0).setPrettyPrinting().create();
        String jsonOutput = gson.toJson(aStudent);
        System.out.println(jsonOutput);

        System.out.println("Student json for Version 1.1 ");
        gson = new GsonBuilder().setVersion(1.1).setPrettyPrinting().create();
        jsonOutput = gson.toJson(aStudent);
        System.out.println(jsonOutput);
    }
}