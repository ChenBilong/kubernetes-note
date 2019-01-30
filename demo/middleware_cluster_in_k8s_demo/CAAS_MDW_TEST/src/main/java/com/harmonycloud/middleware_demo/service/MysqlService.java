/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	StudentServiceImpl.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.harmonycloud.middleware_demo.service;

import com.harmonycloud.middleware_demo.config.DataSourceConfig;
import com.harmonycloud.middleware_demo.config.TargetDateSource;
import com.harmonycloud.middleware_demo.dao.MysqlDao;
import com.harmonycloud.middleware_demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author Administrator
 *
 */
@Service
public class MysqlService {

  @Autowired
  private MysqlDao mysqlDao;

  @TargetDateSource(dataSource = DataSourceConfig.READ_DATASOURCE_KEY)
  public List<Student> findAll() {
    return mysqlDao.findAll();
  }

  @TargetDateSource(dataSource = DataSourceConfig.READ_DATASOURCE_KEY)
  public Student findById(Integer id) {
    Optional<Student> students = mysqlDao.findById(id);
    if (students.isPresent() && students.get() != null) {
      return students.get();
    }
    return null;
  }

  @Transactional
  @TargetDateSource(dataSource = DataSourceConfig.WRITE_DATASOURCE_KEY)
  public Integer save(Student entity) throws Exception {
    if (entity.getId() != null) {
      Student perz = mysqlDao.saveAndFlush(entity);
      return perz.getId();
    }
    Student perz = mysqlDao.save(entity);
    return perz.getId();
  }

}
