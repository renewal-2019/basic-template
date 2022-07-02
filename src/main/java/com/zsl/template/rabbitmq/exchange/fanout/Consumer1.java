package com.zsl.template.rabbitmq.exchange.fanout;

import com.rabbitmq.client.*;
import com.zsl.template.util.RabbitMQFactoryGenerator;

public class Consumer1 {
    private static final String EXCHANGE_NAME = "test_exchange";
    private static final String QUEUE_NAME = "test_exchange_q1";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = RabbitMQFactoryGenerator.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 交换机已存在，不需要创建
//        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //把该队列绑定我们的 exchange 其中 routingkey(也称之为 binding key)为空字符串
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        System.out.println("等待接收消息,把接收到的消息打印在屏幕.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("控制台打印接收到的消息" + message);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
