package com.harmonycloud.middleware_demo.aop;

import com.harmonycloud.middleware_demo.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 数据源的切入面
 *
 */
@Aspect
@Component
@Slf4j
public class DataSourceAOP {

    @Before("(@annotation(com.harmonycloud.middleware_demo.annotation.Master) || execution(* com.harmonycloud.middleware_demo.service..*.insert*(..)) || " +
            "execution(* com.harmonycloud.middleware_demo.service..*.update*(..)) || execution(* com.harmonycloud.middleware_demo.service..*.delete*(..)) || " +
            "execution(* com.harmonycloud.middleware_demo.service..*.add*(..))) && !@annotation(com.harmonycloud.middleware_demo.annotation.Slave) -")
    public void setWriteDataSourceType() {
        DynamicDataSource.master();
        log.info("dataSource切换到：master");
    }

    @Before("(@annotation(com.harmonycloud.middleware_demo.annotation.Slave) || execution(* com.harmonycloud.middleware_demo.service..*.select*(..)) || execution(* com.harmonycloud.middleware_demo.service..*.get*(..))) && !@annotation(com.harmonycloud.middleware_demo.annotation.Master)")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
        log.info("dataSource切换到：slave");
    }


}
