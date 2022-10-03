package com.yangxuan;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenericType<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        GenericType<String> genericType = new GenericType<String>() {
        };
        Type superclass = genericType.getClass().getGenericSuperclass();
        //getActualTypeArguments 返回确切的泛型参数, 如Map<String, Integer>返回[String, Integer]
        Type type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        System.out.println(type);//class java.lang.String
    }

    public static void genArray() {
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(3);
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }
}
