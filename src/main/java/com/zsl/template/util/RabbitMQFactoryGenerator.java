package com.zsl.template.util;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQFactoryGenerator {
    public static ConnectionFactory getFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.103");
        connectionFactory.setUsername("jojo");
        connectionFactory.setPassword("0930zsl");
        return connectionFactory;
    }

}
