package com.yangxuan.dcl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 初始化对象步骤
 * 1. 分配内存空间
 * 2. 初始化对象
 * 3. 设置instance指向刚分配的内存地址
 *
 * 2和3的执行顺序不是固定的 可能发生重排
 *
 */
// https://www.toutiao.com/i6929306249578447363/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1613614689&app=news_article&utm_source=weixin&utm_medium=toutiao_ios&use_new_style=1&req_id=202102181018080102121460775D63E4F5&share_token=16503451-EAB6-4019-AD14-611AFDBAD4D0&group_id=6929306249578447363
public final class Singleton1 {

    private static volatile Singleton1 instance = null;

    // 公平锁：线程获取锁的顺序是按照线程加锁的顺序来分配的 sync = fair ? new FairSync() : new NonfairSync();
    // 非公平锁：获取锁的方式是抢占式的，随机的。默认ReentrantLock()是非公平的 sync = new NonfairSync()
    private static final Lock lock = new ReentrantLock(true);

    private Singleton1() {
        throw new RuntimeException("不能实例化Singleton");
    }

    public static Singleton1 getInstance() {

        if (instance == null) { // 1
            //在线程执行到第1行的时候，代码读取到instance不为null时，instance引用的对象有可能还没有完成初始化(先赋值默认值,再赋值初始值),但是已经赋予了默认值。
            //造成这种现象主要的原因是重排序。重排序是指编译器和处理器为了优化程序性能而对指令序列进行重新排序的一种手段
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

/**
 1. 总线锁 通过lock信号直接锁住总线其他cpu就不能操作该共享变量 使该cpu可以独享此共享内存
 2. MESI缓存一致性协议
 MESI协议：是以 缓存行 (缓存的基本数据单位，在Intel的CPU上一般是64字节)的几个状态来命名的(全名是Modified、Exclusive、 Share or Invalid)。
 该协议要求在每个缓存行上维护两个状态位，使得每个数据单位可能处于M、E、S和I这四种状态之一，各种状态含义如下：

 M：被修改的。处于这一状态的数据，只在本CPU中有缓存数据，而其他CPU中没有。同时其状态相对于内存中的值来说，是已经被修改的，且没有更新到内存中。​
 E：独占的。处于这一状态的数据，只有在本CPU中有缓存，且其数据没有修改，即与内存中一致。
 ​S：共享的。处于这一状态的数据在多个CPU中都有缓存，且与内存一致。
 ​I：无效的。本CPU中的这份缓存已经无效。

 一个处于M状态的缓存行，必须时刻监听所有试图读取该缓存行对应的主存地址的操作，如果监听到，则必须在此操作执行前把其缓存行中的数据写回内存。
 一个处于S状态的缓存行，必须时刻监听使该缓存行无效或者独享该缓存行的请求，如果监听到，则必须把其缓存行状态设置为I。
 一个处于E状态的缓存行，必须时刻监听其他试图读取该缓存行对应的主存地址的操作，如果监听到，则必须把其缓存行状态设置为S。

 当CPU需要读取数据时，如果其缓存行的状态是I的，则需要从内存中读取，并把自己状态变成S，如果不是I，则可以直接读取缓存中的值，
 但在此之前，必须要等待其他CPU的监听结果，如其他CPU也有该数据的缓存且状态是M，则需要等待其把缓存更新到内存之后，再读取。

 ​当CPU需要写数据时，只有在其缓存行是M或者E的时候才能执行，否则需要发出特殊的RFO指令(Read Or Ownership，这是一种总线事务)，
 通知其他CPU置缓存无效(I)，这种情况下性能开销是相对较大的。在写入完成后，修改其缓存状态为M。

 所以如果一个变量在某段时间只被一个线程频繁地修改，则使用其内部缓存就完全可以办到，不涉及到总线事务，如果缓存一会被这个CPU独占、
 一会被那个CPU 独占，这时才会不断产生RFO指令影响到并发性能。这里说的缓存频繁被独占并不是指线程越多越容易触发，
 而是这里的CPU协调机制，这有点类似于有时多线程并不一定提高效率，原因是线程挂起、调度的开销比执行任务的开销还要大，
 这里的多CPU也是一样，如果在CPU间调度不合理，也会形成RFO指令的开销比任务开销还要大。当然，这不是编程者需要考虑的事，操作系统会有相应的内存地址的相关判断


 内存屏障
 编译器和CPU会在不影响结果(这儿主要是根据数据依赖性)的情况下对指令重排序，使性能得到优化，但是实际情况里面有些指令虽然没有前后依赖关系,
 但是重排序之后影响到输出结果,这时候可以插入一个内存屏障，相当于告诉CPU和编译器先后于这个命令的必须先执行，后于这个命令的必须后执行。

 内存屏障的另一个作用是强制更新一次不同CPU的缓存，这意味着如果你对一个volatile字段进行写操作，你必须知道：

 一旦你完成写入，任何访问这个字段的线程将会得到最新的值;
 在你写入之前，会保证所有之前发生的事已经发生，并且任何更新过的数据值也是可见的，因为内存屏障会把之前的写入值都刷新到缓存

 volatile如何保证可见性?

 */