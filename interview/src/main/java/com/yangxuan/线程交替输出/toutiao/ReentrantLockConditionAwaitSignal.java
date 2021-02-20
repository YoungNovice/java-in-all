package com.yangxuan.线程交替输出.toutiao;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionAwaitSignal {

    public static void main(String[] args) {
        char[] digitalArr = "1234567".toCharArray();
        char[] letterArr = "ABCDEFG".toCharArray();
        Lock lock = new ReentrantLock();
        Condition t1 = lock.newCondition();
        Condition t2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char digital : digitalArr) {
                    System.out.println(digital);
                    t2.signal();
                    t1.await();
                }
                t2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char letter : letterArr) {
                    t1.signal();
                    System.out.println(letter);
                    t2.await();
                }
                t1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
