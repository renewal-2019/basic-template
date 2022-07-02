package com.zsl.template.rabbitmq.simple;

import com.rabbitmq.client.*;
import com.zsl.template.util.RabbitMQFactoryGenerator;

import java.util.Calendar;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class AsyncProducer {
    // 队列名称
    public static final String QUEUE_NAME = "ASYNC_test";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = RabbitMQFactoryGenerator.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 开启发布确认
        channel.confirmSelect();

        // 线程安全，有序
        // 存放发送的消息
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

        // 消息确认成功回调函数
        ConfirmCallback ackCallBack = (deliveryTag, multiple) -> {
            // 2.清理已被确认成功发送的消息
            if (multiple) {
                // 如果是批量确认的，则进行批量清理
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            } else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认消息： " + deliveryTag);
        };

        // 消息确认失败回调函数
        ConfirmCallback nackCallBack = (deliveryTag, multiple) -> {
            // 3.处理未确认的消息
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认消息： " + message);
        };
        // 准备消息监听器，监听消息的成功与失败 // 异步
        channel.addConfirmListener(ackCallBack, nackCallBack);

        // 批量发送消息
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(100);
            String message = "Hello world!" + i;
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            // 1.记录所有发送的消息
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }


    }
}
