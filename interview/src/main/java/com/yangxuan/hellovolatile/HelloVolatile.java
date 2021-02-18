package com.yangxuan.hellovolatile;

import java.util.concurrent.TimeUnit;

/**
 * JVM规范 但是hotspot并没有按照规范来
 * ---StoreStoreBarrier---
 * volatile写
 * ---StoreLoadBarrier---
 *
 * S
 * -----
 * S
 * -----
 * L
 *
 * 通过上面的屏障 就可以让 写 -> 写 -> 读 按照顺序来
 *
 * ---LoadLoadBarrier---
 * volatile读
 * ---LoadStoreBarrier---
 *
 *
 * L
 * ----
 * L
 * ----
 * S
 *
 */
public class HelloVolatile {

    // 补加volatile 会running会卡死
    volatile boolean running = true;

    void m() {
        System.out.println("m start " + running);
        // 抢占执行 即使主线程修改了running的值为false 但是由于没有立即获取到 所以拿到的一直是true
        while (running) {

        }
        System.out.println("m end " + running);
    }

    void m2() {
        System.out.println("m2 start " + running);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end " + running);
    }

    public static void main(String[] args) {
        HelloVolatile helloVolatile = new HelloVolatile();
        new Thread(helloVolatile::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        helloVolatile.running = false;
    }
}
