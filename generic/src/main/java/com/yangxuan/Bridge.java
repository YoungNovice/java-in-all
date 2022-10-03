package com.yangxuan;

import java.util.Date;

public class Bridge {
}

//首先定义一个泛型类
class Pair<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

//一个子类继承,设定父类的泛型类型为Pair<Date>
class DateInter extends Pair<Date> {

    @Override
    public void setValue(Date value) {
        super.setValue(value);
    }

    @Override
    public Date getValue() {
        return super.getValue();
    }
}

/**
 * 正常是不能override的
 *
 */