package com.wetsion.study.self_def_config_center;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author weixin
 * @version 1.0
 * @CLassName PropertySourceProcessor
 * @date 2019/10/24 8:30 PM
 */
public class PropertySourceProcessor implements EnvironmentAware, BeanFactoryPostProcessor {

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
