package com.xlong.seckill.controller;

import com.xlong.seckill.SKProcessor.MessageProcessor;
import com.xlong.seckill.mq.RocketMQProvider;
import com.xlong.seckill.redis.RedisRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecKillController {
    @Autowired
    RocketMQProvider rocketMQProvider;

    @Autowired
    RedisRequestHandler redisRequestHandler;

    @Autowired
    MessageProcessor messageProcessor;

    @RequestMapping("/mq-seckill")
    public void mqSeckill(@RequestParam("userId") int userId,
                          @RequestParam("itemId") int itemId) {
        String msg = userId + ":" + itemId;

        // mq
        //rocketMQProvider.defaultMQProducer(msg);

        // redis
        //redisRequestHandler.handle(msg);

        // tradition
        messageProcessor.handleMessage(msg);

        System.out.println("XXX -End- XXX");
    }
}
