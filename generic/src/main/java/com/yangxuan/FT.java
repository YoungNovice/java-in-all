package com.yangxuan;


interface Factory<T> {
    T create();
}
class Creator<T> {
    T instance;
    public <F extends Factory<T>> T newInstance(F f) {
        instance =  f.create();
        return instance;
    }
}
class FactoryImpl implements Factory<Integer> {
    @Override
    public Integer create() {
        return 0;
    }
}

public class FT {
    public static void main(String[] args) {
        Creator<Integer> creator = new Creator<>();
        Integer integer = creator.newInstance(new FactoryImpl());
        System.out.println(integer);
    }

}
