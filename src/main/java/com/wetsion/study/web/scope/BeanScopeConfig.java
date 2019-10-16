package com.wetsion.study.web.scope;

import com.wetsion.study.web.scope.impl.TimeScope;
import com.wetsion.study.web.scope.impl.TimeScopeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weixin
 * @version 1.0
 * @CLassName BeanScopeConfig
 * @date 2019/10/12 10:56 AM
 */
@Configuration
@Slf4j
public class BeanScopeConfig {

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> map = new HashMap<>();
        map.put("timeScope", new TimeScope());
        customScopeConfigurer.setScopes(map);
        return customScopeConfigurer;
    }

    @Bean
    @Scope(value = "prototype")
    public SingletonScopeBean singletonScopeBean() {
        SingletonScopeBean singletonScopeBean = new SingletonScopeBean();
        singletonScopeBean.setCurrentTime(System.currentTimeMillis());
        log.info("singleton bean");
        return singletonScopeBean;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestScopeBean requestScopeBean() {
        RequestScopeBean requestScopeBean = new RequestScopeBean();
        requestScopeBean.setCurrentTime(System.currentTimeMillis());
        log.info("request bean");
        return requestScopeBean;
    }

    @Bean(name = "sessionScopeBean")
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SessionScopeBean sessionScopeBean(RequestScopeBean requestScopeBean) {
        SessionScopeBean sessionScopeBean = new SessionScopeBean();
        sessionScopeBean.setRequestScopeBean(requestScopeBean);
        sessionScopeBean.setCurrentTime(System.currentTimeMillis());
        sessionScopeBean.setRequestTime(requestScopeBean.getCurrentTime());
        log.info("session bean");
        return sessionScopeBean;
    }

    @Bean
    @Scope(value = "timeScope", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TimeScopeBean timeScopeBean() {
        TimeScopeBean timeScopeBean = new TimeScopeBean();
        timeScopeBean.setCurrentTime(System.currentTimeMillis());
        log.info("time scope bean");
        return timeScopeBean;
    }

}
