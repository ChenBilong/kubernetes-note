package com.harmonycloud.middleware_demo.service;

import com.harmonycloud.middleware_demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoServiceTest {
    @Autowired
    private MongoService mongoService;
    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserName("joe");
        user.setPassWord("pwdofjoe");
        user.setPassWord("17699998888");
        user.setEmail("joe@gmail.com");
        user.setSex("MALE");
        user.setComment("use for unittest");
        mongoService.saveUser(user);
    }

    @Test
    public void testFindUserByUserName() throws Exception {
        User user = (User)mongoService.findUserByUserName("joe");
        System.out.println(user.getUserName());
    }


    @Test
    public void testUpdateUser(){
        User user = (User)mongoService.findUserByUserName("joe");
        user.setPassWord("pwdOfJoe");
        mongoService.updateUser(user);
    }

    @Test
    public void testDeleteUserByUserId(){
        mongoService.deleteUserByUserId(4L);
    }
}