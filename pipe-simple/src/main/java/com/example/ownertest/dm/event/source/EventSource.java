package com.example.ownertest.dm.event.source;


import com.example.ownertest.dm.event.event.Event;

/**
 * 发出事件
 * @Author: linear.zw
 * @Date: 2023/11/1 14:12
 */
public interface EventSource {


    /**
     * 发出事件
     *
     * @return
     */
    Event fireEvent();
}