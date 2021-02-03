package com.yangxuan.base;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.Semaphore;

public class ZookeeperBase {

    static final String CONNECT_ADDR = "127.0.0.1:21810";

    static final int SESSION_OUTTIME = 5000;

    static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, event -> {
            // 获取事件状态
            Watcher.Event.KeeperState keeperState = event.getState();
            // 获取事件类型
            Watcher.Event.EventType type = event.getType();
            if (Watcher.Event.KeeperState.SyncConnected == keeperState) {
                if (Watcher.Event.EventType.None == type) {
                    // 如果建立连接成功 则发送信号量 让后续阻塞程序向下执行
                    semaphore.release();
                    System.out.println("zk 建立连接");
                }
            }
        });

        semaphore.acquire();

        // 持久节点
        //String s = zk.create("/yangxuan", "hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //System.out.println(s);

        // 原子消息广播算法

        // 临时节点 默认是本次会话有效
        String s1 = zk.create("/yangxuan/children", "child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(s1);

        Thread.sleep(100000);
        zk.close();
    }
}
