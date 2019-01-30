package com.harmonycloud.middleware_demo.service;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.middleware_demo.model.User;
import com.harmonycloud.middleware_demo.utils.DemoResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;


    public void saveUser(User user) {
        user.setCreateTime(new Date());
        mongoTemplate.save(user);
    }


    public User findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public User findUserById(Long userId){
        Query query = new Query(Criteria.where("id").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public UpdateResult updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        if (!StringUtils.isEmpty(user.getUserName())){
            update.set("userName", user.getUserName());
        }
        if (!StringUtils.isEmpty(user.getPassWord())){
            update.set("passWord", user.getPassWord());
        }
        if (!StringUtils.isEmpty(user.getComment())){
            update.set("comment", user.getComment());
        }
        if (!StringUtils.isEmpty(user.getEmail())){
            update.set("email", user.getEmail());
        }
        if (!StringUtils.isEmpty(user.getPhone())){
            update.set("phone", user.getPhone());
        }
        if (!StringUtils.isEmpty(user.getSex())){
            update.set("sex", user.getSex());
        }
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        // mongoTemplate.updateMulti(query, update, User.class);
        return updateResult;
    }

    public DeleteResult deleteUserByUserId(Long userId) {
        Query query = new Query(Criteria.where("id").is(userId));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        return deleteResult;
    }
}
