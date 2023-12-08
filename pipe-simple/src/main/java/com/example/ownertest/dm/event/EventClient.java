package com.example.ownertest.dm.event;


import com.example.ownertest.dm.event.listener.EventListener;
import com.example.ownertest.dm.event.listener.EventListenerForTest;
import com.example.ownertest.dm.event.source.EventSource;
import com.example.ownertest.dm.event.source.EventSourceForTest;
import com.example.ownertest.dm.event.source.EventSourceForTest2;

/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:19
 */
public class EventClient {


    public static void main(String[] args) {


        // 创建事件源
        EventSource eventSourceForTest = new EventSourceForTest();
        EventSource eventSourceForTest2 = new EventSourceForTest2();


        // 创建事件监听器
        EventListener eventListener = new EventListenerForTest();
        EventListenerManager.addEventListener(eventListener);


        // 发布事件
        EventDispatcher.dispatchEvent(eventSourceForTest.fireEvent());
        EventDispatcher.dispatchEvent(eventSourceForTest2.fireEvent());


    }
}