package com.yangxuan.innerclazz.statics;

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

    public static class Course {

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

public class StaticNestedClassFeature {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Student.Course aCourse = new Student.Course();
        aCourse.setCourseName("M.TECH.");
        aCourse.setDuration("120 hr");

        String jsonCourse = gson.toJson(aCourse);
        System.out.println(jsonCourse);

        Student.Course anotherCourse = gson.fromJson(jsonCourse, Student.Course.class);

        System.out.println("Course : " + anotherCourse.getCourseName() + "Duration : " + anotherCourse.getDuration());
    }
}