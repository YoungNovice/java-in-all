# zookeeper 

## 树型文件系统数据结构

## 节点类型

* 持久化节点 一旦创建 永久存在
* 临时节点 session超时 关闭等 就会被移除
* 持久化顺序节点 在持久化节点的基础上 自带顺序
* 临时顺序节点 在临时节点的基础上 自带顺序
* 容器节点 当没有子节点时 未来会被服务器删除
* TTL节点 过了TTL指定的时间时 被服务器删除

## 事件监听机制
cs 长链接 有服务器推到客户端的事件

## 原子消息广播算法




* 配置中心

* 注册中心
注册 对应create节点 
调用服务 get节点


* 直接基于临时节点的锁  (非公平锁 每次都竞争)
1. 尝试获取锁， 判断锁是否已经存在 如果存在就监听/等待(get -w /lock)
2. 如果不存在就创建锁 (create -e /lock)
3. 如果创建成功就获取到锁 执行业务 然后释放锁(delete /lock)
4. 如果创建失败 则监听/等待(get -w /lock)
这种分布式锁 容易引发羊群效应  (每次只有一个可以获取到锁 但是其他哪些每次都参与竞争 大多数情况下都竞争失败)



* 基于临时顺序子节点的分布式锁 （公平锁 先来先获得）
1. 在节点下创建临时顺序子节点 （create -e -s /lock/x- ）
2. 判断自己是不是最小的那个 
    a.是最小的 获得锁
    b.不是 对前面的节点进行监听(watch)
3. 获得锁得请求 处理完释放锁 (delete /lock/x-0000000000) 然后后继第一个节点将收到通知 重复第二步判断 

https://www.bilibili.com/video/BV1DV411m7kd?p=3
https://www.bilibili.com/video/BV1kt411A7eh/?spm_id_from=333.788.videocard.14