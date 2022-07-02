package com.zsl.template.rabbitmq.exchange.fanout;

import com.rabbitmq.client.*;
import com.zsl.template.util.RabbitMQFactoryGenerator;

import java.util.Scanner;

public class Producer {
    private static final String EXCHANGE_NAME = "test_exchange";
    private static final String QUEUE_NAME = "test_exchange_q2";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = RabbitMQFactoryGenerator.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 开启发布确认
        channel.confirmSelect();

        // 消息确认成功回调函数
        ConfirmCallback ackCallBack = (deliveryTag, multiple) -> {
            // 2.清理已被确认成功发送的消息
            System.out.println("确认消息： " + deliveryTag);
        };

        // 消息确认失败回调函数
        ConfirmCallback nackCallBack = (deliveryTag, multiple) -> {
            // 3.处理未确认的消息
            System.out.println("未确认消息： " + deliveryTag);
        };
        // 准备消息监听器，监听消息的成功与失败 // 异步
        channel.addConfirmListener(ackCallBack, nackCallBack);

        // 发送消息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入信息");
        while (sc.hasNext()) {
            String message = sc.nextLine();
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息" + message);
        }
    }
}
