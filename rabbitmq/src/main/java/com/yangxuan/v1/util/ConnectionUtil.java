package com.yangxuan.v1.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/taotao");
        factory.setUsername("taotao");
        factory.setPassword("taotao");
        return factory.newConnection();
    }
}
