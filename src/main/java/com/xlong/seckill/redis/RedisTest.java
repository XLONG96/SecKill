package com.xlong.seckill.redis;

import com.xlong.seckill.SecKillApplication;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SecKillApplication.class)
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testSet() {
       redisUtils.set("xlong", "awsome");
       System.out.println(redisUtils.get("xlong"));
    }

    @Test
    public void testdel(){

    }

}
