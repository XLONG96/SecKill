package com.xlong.seckill.redis;

import com.xlong.seckill.SKProcessor.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisMsgProcessor implements Runnable {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    MessageProcessor messageProcessor;

    private static final String RQ_LIST = "rqlist";

    @Override
    public void run() {
        String msg = (String)redisUtils.lPop(RQ_LIST);
        System.out.println("XXX-" + msg);
        if (msg != null && msg != "") {
            messageProcessor.handleMessage(msg);
        }
    }
}
