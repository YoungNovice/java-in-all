package com.yangxuan.factory.func;

import com.yangxuan.factory.Audi;
import com.yangxuan.factory.Car;

public class AudiFactory implements Factory {

    @Override
    public Car create() {
        return new Audi();
    }
}
