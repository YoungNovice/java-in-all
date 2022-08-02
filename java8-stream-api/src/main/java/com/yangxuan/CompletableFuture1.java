package com.yangxuan;

import com.sun.xml.internal.ws.util.CompletedFuture;

/**
 * 当长时间计算任务完成时，请将该计算的结果通知到另一个长时间运行的计算任务，这两个计算任务都完成后，将计算的结果与另一个查询操作结果合并
 * 将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果
 * 等待Future集合中的所有任务都完成
 * 仅等待Future集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同一个值），并返回它的结果
 * 通过编程方式完成一个Future任务的执行（即以手工设定异步操作结果的方式）
 * 应对Future的完成事件（即当Future的完成事件发生时会收到通知，并能使用Future计算的结果进行下一步的操作，不只是简单地阻塞等待操作的结果
 */
public class CompletableFuture1 {

    public static void main(String[] args) {

    }

}
