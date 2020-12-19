package com.yangxuan.factory.func;

import com.yangxuan.factory.Bmw;
import com.yangxuan.factory.Car;

/**
* 
* @autoor yangxuan
*/
public class BmwFactory implements Factory {

    @Override
    public Car create() {
        return new Bmw();
    }
}
