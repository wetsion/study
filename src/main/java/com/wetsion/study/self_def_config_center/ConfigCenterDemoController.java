package com.wetsion.study.self_def_config_center;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ConfigCenterDemoController
 * @date 2019/10/24 4:18 PM
 */
@RestController
@Slf4j
public class ConfigCenterDemoController {

    @Autowired
    Environment environment;

    @Value("${test}")
    String test;

    @Autowired
    ConfigurableListableBeanFactory beanFactory;

    @GetMapping("/config/e")
    public void t1() {
        log.info(test);
        log.info(environment.toString());
    }

    @GetMapping("/config/c")
    public void changeE() {
        List<SelfDefConfigValue> selfDefConfigValues =
                SelfDefConfigValueRegistry.getRegistry().get(beanFactory).get("${test}");
        selfDefConfigValues.forEach(selfDefConfigValue -> {
            try {
                selfDefConfigValue.update("weixin");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

}
