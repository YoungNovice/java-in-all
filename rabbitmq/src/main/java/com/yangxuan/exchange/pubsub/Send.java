package com.yangxuan.exchange.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yangxuan.util.ConnectionUtil;

/**
 * 发布订阅模式， 消息先发送到交换机 再到所有       队列
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "商品被更新 id=10011";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] send '" + message + "'");
        channel.close();
        connection.close();
    }
}
