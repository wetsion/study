package com.wetsion.study.self_def_config_center.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wetsion on 2019/10/25.
 */
public class ConfigDataPropertySourceFactory {

    private static final List<ConfigDataPropertySource> CONFIG_DATA_PROPERTY_SOURCE_LIST
            = new ArrayList<>(4);

    public static void addConfigDataPropertySource(ConfigDataPropertySource configDataPropertySource) {
        CONFIG_DATA_PROPERTY_SOURCE_LIST.add(configDataPropertySource);
    }

    public static List<ConfigDataPropertySource> getConfigDataPropertySourceList() {
        return CONFIG_DATA_PROPERTY_SOURCE_LIST;
    }

}
