/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	MysqlDao.java
 * 模块说明：	
 * 修改历史：
 * 2018年9月13日 - Administrator - 创建。
 */
package com.harmonycloud.middleware_demo.dao;


import com.harmonycloud.middleware_demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 *
 */
public interface MysqlDao extends JpaRepository<Student, Integer> {

}
