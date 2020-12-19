package com.yangxuan.factory.simple;


import com.yangxuan.factory.Car;
import com.yangxuan.factory.func.AudiFactory;
import com.yangxuan.factory.func.BmwFactory;
import com.yangxuan.factory.func.Factory;
import org.junit.Test;

public class FactoryTest {

    /**
     * 一个方法根据产品类型生产
     */
    @Test
    public void simpleFactoryTest() {
        SimpleFactory simpleFactoryTest = new SimpleFactory();
        Car car = simpleFactoryTest.create("audi");
        car.run();
    }

    /**
     * 每个工厂生产自己的东西
     */
    @Test
    public void factoryMethod() {
        Factory factory = new AudiFactory();
        Car car = factory.create();
        car.run();

        factory = new BmwFactory();
        Car car1 = factory.create();
        car1.run();
    }

}
