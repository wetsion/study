package com.wetsion.study.self_def_config_center;

import com.wetsion.study.self_def_config_center.config.ConfigDataChangeListener;
import com.wetsion.study.self_def_config_center.config.ConfigDataPropertySource;
import com.wetsion.study.self_def_config_center.config.ConfigDataPropertySourceFactory;
import com.wetsion.study.self_def_config_center.config.PropertySourcesConstants;
import org.springframework.beans.BeansException;
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
public class PropertySourceProcessor implements EnvironmentAware, BeanFactoryPostProcessor {

    private ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.initPropertySources();
        this.initAutoUpdateProperties(beanFactory);
    }

    private void initPropertySources() {
        if (environment.getPropertySources().contains(PropertySourcesConstants.RMS_PROPERTY_SOURCE_NAME)) {
            //already initialized
            return;
        }
        ConfigDataPropertySource configDataPropertySource =
                new ConfigDataPropertySource(PropertySourcesConstants.RMS_PROPERTY_SOURCE_NAME,
                        PropertySourcesConstants.RMS_CONFIG_DATA_REPOSITORY_HTTP);
        ConfigDataPropertySourceFactory.addConfigDataPropertySource(configDataPropertySource);
        environment.getPropertySources().addFirst(configDataPropertySource);
    }

    private void initAutoUpdateProperties(ConfigurableListableBeanFactory beanFactory) {
        ConfigDataChangeListener listener = new ConfigDataChangeListener(
                environment, beanFactory);
        List<ConfigDataPropertySource> configPropertySources = ConfigDataPropertySourceFactory.getConfigDataPropertySourceList();
        for (ConfigDataPropertySource configPropertySource : configPropertySources) {
            configPropertySource.addChangeListener(listener);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }
}
