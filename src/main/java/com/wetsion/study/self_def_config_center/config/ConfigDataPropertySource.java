package com.wetsion.study.self_def_config_center.config;

import com.wetsion.study.self_def_config_center.PlatformBean;
import com.wetsion.study.self_def_config_center.datasource.ConfigDataRepository;
import com.wetsion.study.self_def_config_center.datasource.HttpConfigDataRepository;
import com.wetsion.study.self_def_config_center.datasource.RedisConfigDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;


/**
 * Created by wetsion on 2019/10/25.
 */
@Slf4j
public class ConfigDataPropertySource extends PropertySource<Object> {

    private ConfigDataRepository configDataRepository;

    public ConfigDataPropertySource(String name,
                                    String type,
                                    ConfigDataChangeListener listener,
                                    PlatformBean platformBean) {
        super(name);
        log.info("ConfigDataPropertySource init");
        this.configDataRepository = "redis".equals(type)
                ? new RedisConfigDataRepository()
                : new HttpConfigDataRepository(listener, platformBean);
    }

    @Nullable
    @Override
    public Object getProperty(String name) {
        return configDataRepository.getPlatformConfigDataCache().getConfigurations().getProperty(name);
    }

    public void addChangeListener(ConfigDataChangeListener listener) {
        configDataRepository.addChangeListener(listener);
    }
}
