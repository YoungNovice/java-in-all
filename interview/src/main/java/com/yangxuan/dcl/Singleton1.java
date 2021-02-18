package com.yangxuan.dcl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Singleton1 {

    private static volatile Singleton1 instance;

    // 公平锁：线程获取锁的顺序是按照线程加锁的顺序来分配的 sync = fair ? new FairSync() : new NonfairSync();
    // 非公平锁：获取锁的方式是抢占式的，随机的。默认ReentrantLock()是非公平的 sync = new NonfairSync()
    private static final Lock lock = new ReentrantLock(true);

    private Singleton1() {
        throw new RuntimeException("不能实例化Singleton");
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new Singleton1();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void doSomething() {

    }

}
