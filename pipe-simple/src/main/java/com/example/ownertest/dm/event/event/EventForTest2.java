
package com.example.ownertest.dm.event.event;


/**
 * @Author: linear.zw
 * @Date: 2023/11/1 14:17
 */
public class EventForTest2 implements Event {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}