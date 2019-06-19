package com.wetsion.study.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @CLassName: FactoryBeanConfig
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/11 3:05 PM
 * @Version: 1.0
 */
//@Configuration
public class FactoryBeanConfig {

    @Bean
    public LogFactoryBean logFactoryBean() {
//        return new LogFactoryBean(ILog.class, applicationContext);
        return new LogFactoryBean(ILog.class);
    }

    @Bean
    @Primary
    public ILog logProxy(LogFactoryBean logFactoryBean) throws Exception {
        return (ILog) logFactoryBean.getObject();
    }
}
