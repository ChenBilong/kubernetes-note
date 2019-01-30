package com.harmonycloud.middleware_demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqSenderConf {
    @Bean
    public Queue queue(){
        return new Queue("queue");
    }
}
