package com.example.ownertest.dm.event;


import com.example.ownertest.dm.event.event.Event;
import com.example.ownertest.dm.event.listener.EventListener;

/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:18
 */
public class EventDispatcher {


    /**
     * 单例模式
     */
    private static EventDispatcher eventDispatcher = new EventDispatcher();


    private EventDispatcher() {
    }


    /**
     * 分发事件
     *
     * @param event
     * @return
     */
    public static boolean dispatchEvent(Event event) {
        if (EventListenerManager.getEventListenerList() != null && EventListenerManager.getEventListenerList().size() > 0) {
            for (EventListener eventListener : EventListenerManager.getEventListenerList()) {
                if (eventListener.supportEvent(event)) {
                    eventListener.handlerEvent(event);
                }
            }
        }
        return true;
    }
}