package com.zsl.template.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class Producer {
    // 队列名称
    public static final String QUEUE_NAME = "test2";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.103");
        connectionFactory.setUsername("jojo");
        connectionFactory.setPassword("0930zsl");
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 开启发布确认
        channel.confirmSelect();
        // 通过通道创建队列
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费 是否进行共享 true 可以多个消费者消费
         * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 阻塞
        channel.waitForConfirms();
        // 发送消息
        String message = "Hello world!";
        /**
         * 发送一个消息
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("消息发送完毕。");
    }
}
