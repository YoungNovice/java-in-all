package com.example.ownertest.dm.event.source;


import com.example.ownertest.dm.event.event.Event;
import com.example.ownertest.dm.event.event.EventForTest;
import com.example.ownertest.dm.event.source.EventSource;

/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:14
 */
public class EventSourceForTest implements EventSource {
    @Override
    public Event fireEvent() {


        Event event = new EventForTest();
        System.out.println(getClass().getSimpleName() + " \t fireEvent " + event.getName());


        return event;
    }
}
