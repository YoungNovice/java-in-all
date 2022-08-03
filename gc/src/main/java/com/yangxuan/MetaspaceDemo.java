package com.yangxuan;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MetaspaceDemo {

    static class OOM {
    }

    /**
     * vm options -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+HeapDumpOnOutOfMemoryError
     *
     * @param args .
     */
    public static void main(String[] args) {
        int i = 0;//模拟计数多少次以后发生异常
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("=================多少次后发生异常：" + i);
            e.printStackTrace();
        }
    }
}
