package com.yangxuan.v1.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yangxuan.v1.util.ConnectionUtil;

/**
 * 发布订阅模式， 消息先发送到交换机 通过routingkey再到某个队列
 *
 *   #匹配多个单词 *匹配一个单词
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message = "insert hello world";
        channel.basicPublish(EXCHANGE_NAME, "item.insert", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");

        message = "update hello world";
        channel.basicPublish(EXCHANGE_NAME, "item.update", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");

        message = "delete hello world";
        channel.basicPublish(EXCHANGE_NAME, "item.delete", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");
        channel.close();
        connection.close();
    }
}
