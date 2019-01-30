package com.harmonycloud.middleware_demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 每个请求是一个线程，通过此线程变量来区分使用哪个库
     */
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();


    /**
     * AbstractRoutingDataSource中保存的多个数据源是通过Map的方法保存的。此方法主要是用于确定当前应该使用哪个数据源的key。
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public static enum DatabaseType {
        Master, Slave
    }

    public static void master(){
        contextHolder.set(DatabaseType.Master);
    }


    public static void slave(){
        contextHolder.set(DatabaseType.Slave);
    }

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getType(){
        return contextHolder.get();
    }
}
