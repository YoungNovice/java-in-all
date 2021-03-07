package com.yangxuan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TPETest {
    public volatile static int endNum = -1;

    private static class Task implements Runnable {

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            while (endNum != i) {
                try {
                    TimeUnit.MINUTES.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Task " + i + "结束！");
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    "}";
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        Task t1 = new Task(1);
        tpe.execute(t1);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        Task t2 = new Task(2);
        tpe.execute(t2);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        Task t3 = new Task(3);
        tpe.execute(t3);
        Task t4 = new Task(4);
        tpe.execute(t4);
        Task t5 = new Task(5);
        tpe.execute(t5);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        Task t6 = new Task(6);
        tpe.execute(t6);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        Task t7 = new Task(7);
        tpe.execute(t7);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        Task t8 = new Task(8);
        tpe.execute(t8);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        // 放第9个就使用拒绝策略  9进去了3没了
        // [Task{i=3}, Task{i=4}, Task{i=5}, Task{i=6}]
        // [Task{i=4}, Task{i=5}, Task{i=6}, Task{i=9}]
        Task t9 = new Task(9);
        tpe.execute(t9);
        System.out.println(tpe.getPoolSize());
        System.out.println(tpe.getQueue());
        System.out.println("---------------------");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            endNum = Integer.parseInt(br.readLine().trim());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
