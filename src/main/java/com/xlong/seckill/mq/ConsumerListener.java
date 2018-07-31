package com.xlong.seckill.mq;

import com.xlong.seckill.SKProcessor.MessageProcessor;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConsumerListener implements ServletContextListener {

    @Autowired
    private DefaultMQPushConsumer consumer;

    @Autowired
    private MessageProcessor messageProcessor;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            // 订阅PushTopic下Tag为push的消息
            consumer.subscribe("TopicTest", "push");

            // 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            // 如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            // 注册监听器
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    for (MessageExt messageExt : list) {

                        System.out.println("messageExt: " + messageExt);//输出消息内容

                        String messageBody = new String(messageExt.getBody());

                        System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);//输出消息内容

                        messageProcessor.handleMessage(messageBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });

            // 开启消费者
            consumer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        consumer.shutdown();
    }
}
