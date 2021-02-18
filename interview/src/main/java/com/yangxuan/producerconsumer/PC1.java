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
public class PC1 {

    private final static int MAX = 10;
    public List<Object> list = new LinkedList<>();
    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public void add() {
        try {
            lock.lock();
            while (list.size() == MAX) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(1);
            System.out.println("生产者生产"+Thread.currentThread().getName()+" 现库存为："+getCount());
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            while (list.size() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove(0);
            System.out.println("消费者消费"+Thread.currentThread().getName()+" 现库存为："+getCount());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        try {
            lock.lock();
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}


