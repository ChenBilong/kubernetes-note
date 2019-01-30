package com.harmonycloud.middleware_demo.service;

import com.harmonycloud.middleware_demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqServiceTest {
    @Autowired
    private RabbitMqService rabbitMqService;
    @Test
    public void testSend() throws Exception {
        rabbitMqService.sendMessages(100);
        Thread.sleep(500);

    }

}