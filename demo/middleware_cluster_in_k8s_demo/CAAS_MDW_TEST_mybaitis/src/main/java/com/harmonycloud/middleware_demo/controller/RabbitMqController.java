package com.harmonycloud.middleware_demo.controller;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.middleware_demo.service.KafkaService;
import com.harmonycloud.middleware_demo.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rabbit")
public class RabbitMqController {
    @Autowired
    private RabbitMqService rabbitMqService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity send(@RequestParam int num) {
        rabbitMqService.sendMessages(num);
        return new ResponseEntity("请求已提交", HttpStatus.OK);
    }
}
