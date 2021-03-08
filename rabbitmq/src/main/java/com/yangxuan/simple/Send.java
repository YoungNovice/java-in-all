package com.yangxuan.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yangxuan.util.ConnectionUtil;

/**
 * 一个生产一个消费 不需要交换机 只用队列就可以实现
 */
public class Send {

    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "hello world232343244233";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");
        channel.close();
        connection.close();
    }
}
