package com.harmonycloud.middleware_demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid的DataResource配置类
 * @author Raye
 * @since 2016年10月7日14:14:18
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration  implements EnvironmentAware {

    private String masterUrl = "jdbc:mysql://10.10.124.199:31036/demo?useUnicode=true&amp;characterEncoding=utf8&amp;emptyStringsConvertToZero=true";
    private String slaveUrl = "jdbc:mysql://10.10.124.199:32036/demo?useUnicode=true&amp;characterEncoding=utf8&amp;emptyStringsConvertToZero=true";
    private String userName = "root";
    private String password = "";
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String type = "com.alibaba.druid.pool.DruidDataSource";
    private int maxActive = 20;
    private int initialSize = 1;
    private int minIdle = 3;
    private int maxWait = 600;
    private String timeBetweenEvictionRunsMillis = "60000";
    private long minEvictableIdleTimeMillis = 300000L;
    private String testWhileIdle = "true";
    private String testOnBorrow = "false";
    private String testOnReturn = "false";
    private String poolPreparedStatements = "true";

    public DataBaseConfiguration(){
        System.out.println("####################  DataBaseConfiguration");
    }
    public void setEnvironment(Environment env) {

    }

    public DataSource master() {
        System.out.println("inject master druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(masterUrl);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxWait(maxWait);
        datasource.setMaxActive(maxActive);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    public DataSource slave() {
        System.out.println("Slave druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(slaveUrl);
        datasource.setDriverClassName(driverClassName);
        datasource.setUsername(userName);
        datasource.setPassword(password);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxWait(maxWait);
        datasource.setMaxActive(maxActive);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource() {
        DataSource master = master();
        DataSource slave = slave();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DynamicDataSource.DatabaseType.Master, master);
        targetDataSources.put(DynamicDataSource.DatabaseType.Slave, slave);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }


}
