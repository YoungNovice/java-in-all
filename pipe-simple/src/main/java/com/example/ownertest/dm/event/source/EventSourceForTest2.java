
package com.example.ownertest.dm.event.source;


import com.example.ownertest.dm.event.event.Event;
import com.example.ownertest.dm.event.event.EventForTest2;
import com.example.ownertest.dm.event.source.EventSource;

/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:15
 */
public class EventSourceForTest2 implements EventSource {
    @Override
    public Event fireEvent() {


        Event event = new EventForTest2();
        System.out.println(getClass().getSimpleName() + " \t fireEvent " + event.getName());


        return event;
    }
}