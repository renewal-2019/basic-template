package com.zsl.template.rabbitmq.高级发布确认;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmConsumer implements ChannelAwareMessageListener {
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";

//    @RabbitListener(queues = CONFIRM_QUEUE_NAME)
//    public void receiveMsg(Message message) {
//        String msg = new String(message.getBody());
//        log.info("接受到队列 confirm.queue 消息:{}", msg);
//    }

    /**
     * 消息处理成功，调用basicAck
     * 处理失败，调用basicNack，broker重新发送给consumer
     *
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = CONFIRM_QUEUE_NAME)
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody());
        try {
            log.info("接受到队列 confirm.queue 消息:{}", msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.warn("return=========!!!!!!!!!!!!!!!");
            // 失败调用，并让消息重新入队
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

    }
}
