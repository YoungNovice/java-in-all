package com.yangxuan;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Arr {

    public static void main(String[] args) {

    }

    public static void m1(String[] args) {
        List<String>[] lsa = null; //new List<String>[10]; // Not really allowed.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        String s = lsa[1].get(0); // Run-time error ClassCastException.
    }

    public static void m2(String[] args) {
        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }

    public static void m3(String[] args) {

    }


}

class ArrayWithTypeToken<T> {

    private final T[] array;

    public ArrayWithTypeToken(Class<T> type, int size) {
        array = (T[]) Array.newInstance(type, size);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] create() {
        return array;
    }

    public static void main(String[] args) {
        ArrayWithTypeToken<Integer> arrayToken = new ArrayWithTypeToken<>(Integer.class, 100);
        Integer[] arr = arrayToken.create();
    }

}




