package com.wetsion.study.self_def_config_center.config;

import com.wetsion.study.self_def_config_center.datasource.ConfigDataRepository;
import com.wetsion.study.self_def_config_center.datasource.HttpConfigDataRepository;
import com.wetsion.study.self_def_config_center.datasource.RedisConfigDataRepository;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;


/**
 * Created by wetsion on 2019/10/25.
 */
public class ConfigDataPropertySource extends PropertySource<Object> {

    private ConfigDataRepository configDataRepository;

    public ConfigDataPropertySource(String name, String type) {
        super(name);
        this.configDataRepository = "redis".equals(type)
                ? new RedisConfigDataRepository() : new HttpConfigDataRepository();
    }

    @Nullable
    @Override
    public Object getProperty(String name) {
        return configDataRepository.getPlatformConfigDataCache().getConfigurations().get(name);
    }

    public void addChangeListener(ConfigDataChangeListener listener) {
        configDataRepository.addChangeListener(listener);
    }
}
