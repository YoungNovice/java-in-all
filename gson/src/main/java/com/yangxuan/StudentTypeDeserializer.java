package com.yangxuan;

import com.google.gson.*;

import java.lang.reflect.Type;

class StudentTypeDeserializer implements JsonDeserializer<Student> {

    @Override
    public Student deserialize(JsonElement jsonelment, Type type,
                               JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = jsonelment.getAsJsonObject();

        Student aStudent = new Student();
        aStudent.setName(jsonObject.get("studentname").getAsString());
        aStudent.setSubject(jsonObject.get("subjecttaken").getAsString());
        aStudent.setMark(jsonObject.get("marksecured").getAsInt());

        return aStudent;
    }
}