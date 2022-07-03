package com.zsl.template.rabbitmq.高级发布确认;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    public static final String TTL_EXCHANGE_NAME = "ttl.exchange";
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";
    public static final String TTL_QUEUE_NAME = "ttl.queue";

    //声明业务 Exchange
    @Bean("confirmExchange")
    public Exchange confirmExchange() {
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true).build();
    }

    //声明业务 Exchange
    @Bean("ttlExchange")
    public Exchange ttlExchange() {
        return ExchangeBuilder.directExchange(TTL_EXCHANGE_NAME).durable(true).build();
    }

    // 声明确认队列
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    // 用于存放ttl消息
    @Bean("ttlQueue")
    public Queue ttlQueue() {
        return QueueBuilder.durable(TTL_QUEUE_NAME).build();
    }

    // 声明确认队列绑定关系
    @Bean
    public Binding queueBinding(@Qualifier("confirmQueue") Queue queue,
                                @Qualifier("confirmExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("key1").noargs();
    }

    // 声明确认队列绑定关系
    @Bean
    public Binding queueTtlBinding(@Qualifier("ttlQueue") Queue queue,
                                   @Qualifier("ttlExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("ttl").noargs();
    }
}
