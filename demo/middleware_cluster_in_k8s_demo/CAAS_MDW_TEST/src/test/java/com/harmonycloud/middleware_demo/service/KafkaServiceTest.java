package com.harmonycloud.middleware_demo.service;

import com.harmonycloud.middleware_demo.model.Message;
import com.harmonycloud.middleware_demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaServiceTest {
    @Autowired
    private KafkaService kafkaService;
    @Test
    public void send() throws Exception {
        kafkaService.sendMessages(100);
        Thread.sleep(30000);
    }

}