package com.harmonycloud.middleware_demo.controller;

import com.harmonycloud.middleware_demo.model.User;
import com.harmonycloud.middleware_demo.service.MySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebResult;

@Controller
@RequestMapping("/mysql")
public class MysqlController {
    @Autowired
    private MySqlService mySqlService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity add(User user){
        if(mySqlService.addUser(user)){
            return new  ResponseEntity("添加成功", HttpStatus.OK);
        }else{
            return new ResponseEntity("添加失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity update(User user){
        if(mySqlService.updateUser(user)){
            return new ResponseEntity("更新成功", HttpStatus.OK);
        }else{
            return new ResponseEntity ("更新失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(int id){
        if(mySqlService.deleteByid(id)){
            return new ResponseEntity("删除成功", HttpStatus.OK);
        }else{
            return new ResponseEntity("删除失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public ResponseEntity select(){
        return new ResponseEntity(mySqlService.selectAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ResponseEntity test(User user){
        try {
            if(mySqlService.insertAndUpdate(user)) {
                return new ResponseEntity(HttpStatus.OK);
            }else {
                return new ResponseEntity("失败,查看事务", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("失败:"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
