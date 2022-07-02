package com.zsl.template.rabbitmq.simple;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer {
    private final static String QUEUE_NAME = "test";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.103");
        factory.setUsername("jojo");
        factory.setPassword("0930zsl");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息....");

        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            // 业务层消费消息
            String message = new String(delivery.getBody());
            System.out.println(message);
            // 1.消息标记tag 2.false代表只应答接收到的那个消息 true代表应答所有消息包括传递来的消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };

        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, cancelCallback);
    }
}
