package com.yangxuan.innerclazz.non;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Student {

    private String studentName;
    private int mark;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public class Course {

        private String courseName;

        private String duration;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }
}

public class InstanceNestedClassFeature {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Student outstudent = new Student();
        Student.Course instanceCourse = outstudent.new Course();

        instanceCourse.setCourseName("M.TECH.");
        instanceCourse.setDuration("12 hr");

        String jsonCourse = gson.toJson(instanceCourse);
        System.out.println(jsonCourse);

        Student.Course anotherCourse = gson.fromJson(jsonCourse, Student.Course.class);
        System.out.println("Course : " + anotherCourse.getCourseName() + "Duration : " + anotherCourse.getDuration());
    }
}