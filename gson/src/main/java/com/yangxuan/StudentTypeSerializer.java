package com.yangxuan;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StudentTypeSerializer implements JsonSerializer<Student> {

    @Override
    public JsonElement serialize(Student student, Type type,
                                 JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        obj.addProperty("studentname", student.getName());
        obj.addProperty("subjecttaken", student.getSubject());
        obj.addProperty("marksecured", student.getMark());

        return obj;
    }
}