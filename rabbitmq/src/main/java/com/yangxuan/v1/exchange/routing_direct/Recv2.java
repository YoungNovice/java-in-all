package com.yangxuan.v1.exchange.routing_direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.yangxuan.v1.util.ConnectionUtil;

public class Recv2 {

    private static final String QUEUE_NAME = "test_queue_direct_2";
    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert");
        // 同一个时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {
            // 这里会阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [消费者2] receive '" + message + "'");
            Thread.sleep(1000);
            // 返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
