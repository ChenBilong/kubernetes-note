/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	StudentController.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.harmonycloud.middleware_demo.controller;

import com.harmonycloud.middleware_demo.model.Student;
import com.harmonycloud.middleware_demo.service.MysqlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Administrator
 *
 */
@Api(value = "学生管理接口")
@RestController
@RequestMapping("/mysql/*")
public class MysqlController {

  @Autowired
  private MysqlService mysqlService;

  @ApiOperation(value = "获取所有的学生信息", httpMethod = "GET")
  @RequestMapping("findAll")
  public List<Student> findAll() {
    return mysqlService.findAll();
  }

  @ApiOperation(value = "获取学生信息", notes = "根据url的id获取学生信息", httpMethod = "GET")
  @ApiImplicitParam(name = "id", value = "学生唯一标识", required = true, dataType = "Integer",
      paramType = "path")
  @RequestMapping(value = "{id}", method = RequestMethod.GET)
  public Student findById(@PathVariable("id") Integer id) {
    return mysqlService.findById(id);
  }

  @ApiOperation(value = "新增/更新学生信息", notes = "id为空时表示新增，否则为更新", httpMethod = "POST")
  @ApiImplicitParam(name = "entity", value = "学生实体类", required = true, dataType = "Student")
  @RequestMapping(value = "save", method = RequestMethod.POST)
  public Integer save(@RequestBody Student entity) throws Exception {
    return mysqlService.save(entity);
  }
}
