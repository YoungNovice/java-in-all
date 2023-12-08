package com.example.ownertest.dm.event.listener;


import com.example.ownertest.dm.event.event.Event;

/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:17
 */
public interface EventListener {


    /**
     * 是否支持此事件
     *
     * @param event
     * @return
     */
    boolean supportEvent(Event event);


    /**
     * 处理事件
     *
     * @return
     */
    boolean handlerEvent(Event event);
}