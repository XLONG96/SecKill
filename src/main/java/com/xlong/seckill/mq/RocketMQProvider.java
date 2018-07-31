package com.xlong.seckill.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

@Service
public class RocketMQProvider {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DefaultMQProducer producer;

    public void defaultMQProducer(String cilentMsg) {

        try {
            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
            Message message = new Message("TopicTest", "push", cilentMsg.getBytes());

            SendResult result = producer.send(message, (List<MessageQueue> mqs, Message msg, Object arg) -> {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
            },1);

            System.out.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}