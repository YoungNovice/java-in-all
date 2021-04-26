
package com.yangxuan.generic;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class StudentGeneric<T, E> {

    T mark;

    E name;

    public T getMark() {
        return mark;
    }

    public void setMark(T mark) {
        this.mark = mark;
    }

    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}

public class GenericTypeFeature {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Gson gson = new Gson();
        StudentGeneric<Integer, String> studGenericObj1 = new StudentGeneric<Integer, String>();
        studGenericObj1.setMark(25);
        studGenericObj1.setName("Sandeep");

        String json = gson.toJson(studGenericObj1);
        System.out.println("Serialized Output :");
        System.out.println(json);

        StudentGeneric<Integer, String> studGenericObj2 = gson.fromJson(json, StudentGeneric.class);
        System.out.println("DeSerialized Output :");
        System.out.println("Mark : " + studGenericObj2.getMark());

        Type studentGenericType = new TypeToken<StudentGeneric<Integer, String>>() {}.getType();

        StudentGeneric<Integer, String> studGenericObj3 = gson.fromJson(json, studentGenericType);
        System.out.println("TypeToken Use DeSerialized Output :");
        System.out.println("Mark : " + studGenericObj3.getMark());
    }

}