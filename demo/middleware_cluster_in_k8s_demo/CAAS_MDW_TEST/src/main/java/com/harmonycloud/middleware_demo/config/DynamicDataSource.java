/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	DynamicDataSource.java
 * 模块说明：	
 * 修改历史：
 * 2018年10月21日 - Administrator - 创建。
 */
package com.harmonycloud.middleware_demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Administrator
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    // 可以做一个简单的负载均衡策略
    String lookupKey = DynamicDataSourceHolder.getDataSource();
    System.out.println("------------lookupKey---------" + lookupKey);
    return lookupKey;
  }
}
