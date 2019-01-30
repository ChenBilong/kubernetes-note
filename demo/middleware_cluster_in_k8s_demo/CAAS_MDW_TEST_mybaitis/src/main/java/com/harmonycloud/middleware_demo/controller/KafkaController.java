package com.harmonycloud.middleware_demo.controller;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.middleware_demo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity send(@RequestParam int num) {
        kafkaService.sendMessages(num);
        return new ResponseEntity("请求已提交", HttpStatus.OK);
    }
}
