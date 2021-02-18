package com.yangxuan.producerconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 其实用一个condition也可以实现
 *
 * @author yangxuan
 */
public class PC2 {

    private final static int MAX = 10;
    public final List<Object> list = new LinkedList<>();

    public void add() {
        synchronized (list) {
            while (list.size() == MAX) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(1);
            System.out.println("生产者生产"+Thread.currentThread().getName()+" 现库存为："+getCount());
            list.notifyAll();
        }
    }

    public void get() {
        synchronized (list) {
            while (list.size() == 0) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove(0);
            System.out.println("消费者消费"+Thread.currentThread().getName()+" 现库存为："+getCount());
            list.notifyAll();
        }
    }

    public synchronized int getCount() {
        return list.size();
    }
}


