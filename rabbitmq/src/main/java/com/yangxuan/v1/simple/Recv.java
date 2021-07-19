package com.yangxuan.v1.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.yangxuan.v1.util.ConnectionUtil;

public class Recv {

    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列 autoAck false消息就不会消费
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            // 这里会阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] receive '" + message + "'");
        }
    }
}
