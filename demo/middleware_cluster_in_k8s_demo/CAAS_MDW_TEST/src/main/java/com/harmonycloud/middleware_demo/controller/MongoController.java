package com.harmonycloud.middleware_demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.harmonycloud.middleware_demo.model.User;
import com.harmonycloud.middleware_demo.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/mongo")
public class MongoController {
    @Autowired
    private MongoService mongoService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity save(@RequestBody User user) {
        mongoService.saveUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findUserByUserName(@PathVariable String userName) {
        return new ResponseEntity(mongoService.findUserByUserName(userName), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findUserByUserId(@RequestParam Long userId) {
        return new ResponseEntity(mongoService.findUserById(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user) {
        if (Objects.isNull(user.getId())){
            return new ResponseEntity("must provide id", HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(mongoService.findUserById(user.getId()))){
            return new ResponseEntity("can't find the user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(mongoService.updateUser(user),HttpStatus.OK);
    }


    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteUserByUserId(@RequestParam long  userId) {
        return new ResponseEntity(mongoService.deleteUserByUserId(userId),HttpStatus.OK);

    }
}
