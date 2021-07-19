package com.yangxuan.v1.exchange.routing_direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yangxuan.v1.util.ConnectionUtil;

/**
 * 发布订阅模式， 消息先发送到交换机 通过routingkey再到某个队列
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = "insert hello world";
        channel.basicPublish(EXCHANGE_NAME, "insert", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");

        message = "update hello world";
        channel.basicPublish(EXCHANGE_NAME, "update", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");

        message = "delete hello world";
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");
        channel.close();
        connection.close();
    }
}
