package com.wetsion.study.web.config;

import com.wetsion.study.self_def_config_center.PlatformBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weixin
 * @version 1.0
 * @CLassName BeanConfig
 * @date 2019/10/25 11:08 AM
 */
@Configuration
public class BeanConfig {

    @Bean
    public PlatformBean platformBean() {
        PlatformBean platformBean = new PlatformBean();
        platformBean.setClientId("4");
        platformBean.setConfigUrl("http://localhost:7001/open/config/get");
        return platformBean;
    }
}
