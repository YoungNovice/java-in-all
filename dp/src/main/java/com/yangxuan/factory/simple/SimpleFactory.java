package com.yangxuan.factory.simple;

import com.yangxuan.factory.Audi;
import com.yangxuan.factory.Bmw;
import com.yangxuan.factory.Car;

/**
* 简单工厂
* @autoor yangxuan
*/
public class SimpleFactory {

    public Car create(String type) {
        switch (type) {
            case "audi":
                return new Audi();
            case "bmw":
                return new Bmw();
            default:
                throw new RuntimeException("unknown");
        }
    }
}
