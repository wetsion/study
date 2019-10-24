package com.wetsion.study.self_def_config_center.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;

/**
 * Created by wetsion on 2019/10/25.
 */
public class ConfigDataChangeListener {

    public ConfigDataChangeListener(Environment environment, ConfigurableListableBeanFactory beanFactory) {

    }

    public void onChange() {}
}
