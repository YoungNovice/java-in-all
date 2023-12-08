package com.example.ownertest.dm.event;


import java.util.List;


import com.example.ownertest.dm.event.listener.EventListener;
import com.google.common.collect.Lists;


/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:18
 */
public class EventListenerManager {


    private static List<EventListener> eventListenerList = Lists.newArrayList();


    /**
     * 添加事件监听器
     *
     * @param eventListener
     * @return
     */
    public static boolean addEventListener(EventListener eventListener) {
        if (!eventListenerList.contains(eventListener)) {
            return eventListenerList.add(eventListener);
        }


        return true;
    }


    /**
     * 移除事件监听器
     *
     * @param eventListener
     * @return
     */
    public static boolean removeEventListener(EventListener eventListener) {
        if (eventListenerList.contains(eventListener)) {
            return eventListenerList.remove(eventListener);
        }


        return true;
    }


    public static List<EventListener> getEventListenerList() {
        return eventListenerList;
    }
}