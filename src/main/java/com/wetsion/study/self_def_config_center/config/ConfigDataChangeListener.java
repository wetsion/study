package com.wetsion.study.self_def_config_center.config;

import com.wetsion.study.self_def_config_center.PlatformConfigDataCache;
import com.wetsion.study.self_def_config_center.SelfDefConfigValue;
import com.wetsion.study.self_def_config_center.SelfDefConfigValueRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;

import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 *
 *
 * @author weixin
 * @date 2:08 PM 2019/10/25
 **/
@Slf4j
public class ConfigDataChangeListener {

    private final Environment environment;

    private final ConfigurableBeanFactory beanFactory;

    public ConfigDataChangeListener(Environment environment, ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.environment = environment;
    }

    public void onChange(PlatformConfigDataCache newData) {
        Properties properties = newData.getConfigurations();
        Enumeration names = properties.propertyNames();
        while (names.hasMoreElements()) {
            String key = (String) names.nextElement();

            List<SelfDefConfigValue> selfDefConfigValues =
                    SelfDefConfigValueRegistry.getRegistry()
                            .get(beanFactory)
                            .get(PropertySourcesConstants.RMS_CONFIG_PROPERTY_PREFIX
                                    + key
                                    + PropertySourcesConstants.RMS_CONFIG_PROPERTY_SUBFIX);
            if (Objects.nonNull(selfDefConfigValues)) {
                selfDefConfigValues.forEach(selfDefConfigValue -> {
                    try {
                        selfDefConfigValue.update(properties.getProperty(key));
                    } catch (IllegalAccessException e) {
                        log.error("更新 @value 失败, bean name: [{}], key: [{}]",
                                selfDefConfigValue.getBeanName(), selfDefConfigValue.getKey(), e);
                    }
                });
            }
        }

    }
}
