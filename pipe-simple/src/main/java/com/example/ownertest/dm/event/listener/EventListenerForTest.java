package com.example.ownertest.dm.event.listener;


import com.example.ownertest.dm.event.event.Event;

/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:18
 */
public class EventListenerForTest implements EventListener {
    @Override
    public boolean supportEvent(Event event) {


        return event.getName().contains("Test");
    }


    @Override
    public boolean handlerEvent(Event event) {


        System.out.println(this.getClass().getSimpleName() + "\t handler " + event.getName());


        return true;
    }
}