package com.xlong.seckill.redis;

import com.xlong.seckill.SKProcessor.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

public class RedisRequestHandler {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    MessageProcessor messageProcessor;

    private static final String STORE = "store";

    private static final String RQ_LIST = "rqlist";

    private static ExecutorService pool = new ThreadPoolExecutor(5, 10,
            10L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(300));

    public void handle(String msg) {
        int store = (int)redisUtils.get(STORE);
        if (store > 0) {
            // 将请求数据写入redis
            redisUtils.lPush(RQ_LIST, msg);
            // 库存减1
            redisUtils.set(STORE, --store);
            // 提交任务
            pool.submit(() -> {

                String pmsg = (String)redisUtils.lPop(RQ_LIST);
                System.out.println("XXX" + pmsg);
                if (pmsg != null && pmsg != "") {
                    messageProcessor.handleMessage(pmsg);
                }

            });
        }
    }
}
