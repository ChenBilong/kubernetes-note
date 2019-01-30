/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	DynamicDataSourceHolder.java
 * 模块说明：	
 * 修改历史：
 * 2018年10月21日 - Administrator - 创建。
 */
package com.harmonycloud.middleware_demo.config;

/**
 * @author Administrator
 *
 */
public class DynamicDataSourceHolder {
  // 使用ThreadLocal把数据源与当前线程绑定
  private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();

  public static void setDataSource(String dataSourceName) {
    dataSources.set(dataSourceName);
  }

  public static String getDataSource() {
    return (String) dataSources.get();
  }

  public static void clearDataSource() {
    dataSources.remove();
  }
}
