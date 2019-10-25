package com.wetsion.study.self_def_config_center;

import com.wetsion.study.self_def_config_center.config.ConfigDataChangeListener;
import com.wetsion.study.self_def_config_center.config.ConfigDataPropertySource;
import com.wetsion.study.self_def_config_center.config.ConfigDataPropertySourceFactory;
import com.wetsion.study.self_def_config_center.config.PropertySourcesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Iterator;
import java.util.List;

/**
 * @author weixin
 * @version 1.0
 * @CLassName PropertySourceProcessor
 * @date 2019/10/24 8:30 PM
 */
@Slf4j
public class PropertySourceProcessor implements EnvironmentAware, BeanFactoryPostProcessor {

    PlatformBean platformBean;

    private ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info(environment.getProperty("client-id"));
        platformBean = (PlatformBean) beanFactory.getBean("platformBean");
        log.info(platformBean.getClientId());
        this.initPropertySources(beanFactory);
    }

    private void initPropertySources(ConfigurableListableBeanFactory beanFactory) {
        if (environment.getPropertySources().contains(PropertySourcesConstants.RMS_PROPERTY_SOURCE_NAME)) {
            // 已初始化
            return;
        }
        ConfigDataChangeListener listener = new ConfigDataChangeListener(
                environment, beanFactory);
        ConfigDataPropertySource configDataPropertySource =
                new ConfigDataPropertySource(PropertySourcesConstants.RMS_PROPERTY_SOURCE_NAME,
                        PropertySourcesConstants.RMS_CONFIG_DATA_REPOSITORY_HTTP,
                        listener, platformBean);
        ConfigDataPropertySourceFactory.addConfigDataPropertySource(configDataPropertySource);
        environment.getPropertySources().addFirst(configDataPropertySource);
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }
}
