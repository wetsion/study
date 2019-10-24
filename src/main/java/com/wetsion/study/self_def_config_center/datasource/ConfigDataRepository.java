package com.wetsion.study.self_def_config_center.datasource;

import com.wetsion.study.self_def_config_center.PlatformConfigDataCache;
import com.wetsion.study.self_def_config_center.config.ConfigDataChangeListener;

/**
 * Created by wetsion on 2019/10/25.
 */
public interface ConfigDataRepository {

    void syncData();

    void scheduleRefreshData();

    void addChangeListener(ConfigDataChangeListener listener);

    PlatformConfigDataCache getPlatformConfigDataCache();
}
