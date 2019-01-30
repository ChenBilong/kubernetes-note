package com.harmonycloud.middleware_demo.service;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.middleware_demo.model.User;
import com.harmonycloud.middleware_demo.utils.DemoResult;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitMqService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(User user) {
        amqpTemplate.convertAndSend("queue", user);
    }

    @RabbitListener(queues = "queue")
    public void processC(User user) {
        System.out.println("RABBITMQ-Receiver: " + JSON.toJSONString(user));
    }

    public DemoResult sendMessages(int num) {
        System.out.println("==================================== RABBIT SEND MESSAGES NOW ====================================");
        User user = new User();
        for (int i = 0; i < num; i++) {
            user.setId(1000000L + i);
            user.setUserName("name" + i);
            user.setPhone("028-" + i);
            user.setEmail(user.getUserName() + "@gmail.com");
            user.setSex("S_FEMALE");
            user.setComment("from rabbit sender");
            user.setCreateTime(new Date());
            send(user);
        }
        System.out.println("==================================== RABBIT SEND MESSAGES COMPLETE ====================================");
        DemoResult res = new DemoResult();
        res.setCode("200");
        res.setMsg("发送成功");
        return res;
    }
}