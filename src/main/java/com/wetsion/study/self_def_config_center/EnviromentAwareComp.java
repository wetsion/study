package com.wetsion.study.self_def_config_center;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author weixin
 * @version 1.0
 * @CLassName EnviromentAwareComp
 * @date 2019/10/24 4:38 PM
 */
//@Component
public class EnviromentAwareComp implements EnvironmentAware, BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        ((ConfigurableEnvironment)environment).getPropertySources()
                .addFirst(new PropertySource<String>("testConfig") {
                    @Override
                    public Object getProperty(String name) {
                        if ("test".equals(name)) {
                            return "test";
                        }
                        return null;
                    }
                });
    }
}
