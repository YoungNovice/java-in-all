package com.yangxuan.v1.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yangxuan.v1.util.ConnectionUtil;

/**
 * 一个生产多个消费 销售靠抢， 只会被一个消费者消费
 */
public class Send {

    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 100; i++) {
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] send '" + message + "'");

            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }
}
