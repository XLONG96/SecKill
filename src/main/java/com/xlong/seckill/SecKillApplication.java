package com.xlong.seckill;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@ServletComponentScan
@EnableCaching
@SpringBootApplication
public class SecKillApplication {
    public static void main(String[] args){
        SpringApplication.run(SecKillApplication.class, args);
    }

    @Bean
    public ObjectMapper ObjectMapper(){
        return new ObjectMapper();
    }
}
