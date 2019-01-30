package com.harmonycloud.middleware_demo.service;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.middleware_demo.model.Message;
import com.harmonycloud.middleware_demo.model.User;
import com.harmonycloud.middleware_demo.utils.DemoResult;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class KafkaService {
    private Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(id = "tut", topics = "kafka.tut")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        // 判断是否null
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()){
            // 获取消息
            Object message = kafkaMessage.get();

            System.out.println("KAFKA-Receiver: Topic: " + topic);
            System.out.println("KAFKA-Receiver: Record: " + record);
            System.out.println("KAFKA-Receiver: Message: " + message);
        }
    }

    public void send(User user){
        String jsonMsg = JSON.toJSONString(user);
        System.out.println("KAFKA-Producer: Ready to send message: " + jsonMsg);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka.tut", jsonMsg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("KAFKA-Producer: The message failed to be sent: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(@Nullable SendResult<String, Object> stringObjectSendResult) {
                System.out.println("KAFKA-Producer: The message was sent successfully:");
                System.out.println("KAFKA-Producer: " + stringObjectSendResult.toString());

            }
        });

    }


    public void sendMessages(int n){
        System.out.println("==================================== KAFKA SEND MESSAGES NOW ====================================");
        User user = new User();
        for (int i =0;i<n;i++){
            user.setId(1000000L + i);
            user.setUserName("name" + i);
            user.setPhone("028-" + i);
            user.setEmail(user.getUserName() + "@gmail.com");
            user.setSex("S_FEMALE");
            user.setComment("from kafka sender");
            user.setCreateTime(new Date());
            send(user);
        }
        System.out.println("================================= KAFKA SEND MESSAGES COMPLETE ==================================");
    }

}
