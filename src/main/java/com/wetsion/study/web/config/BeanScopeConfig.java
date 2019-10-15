package com.wetsion.study.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

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
}
