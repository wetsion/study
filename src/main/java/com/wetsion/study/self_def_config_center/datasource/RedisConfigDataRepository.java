package com.wetsion.study.self_def_config_center.datasource;

import com.wetsion.study.self_def_config_center.PlatformConfigDataCache;
import com.wetsion.study.self_def_config_center.config.ConfigDataChangeListener;

/**
 * Created by wetsion on 2019/10/25.
 */
public class RedisConfigDataRepository implements ConfigDataRepository {
    @Override
    public void syncData() {

    }

    @Override
    public void scheduleRefreshData() {

    }

    @Override
    public void addChangeListener(ConfigDataChangeListener listener) {

    }

    @Override
    public PlatformConfigDataCache getPlatformConfigDataCache() {
        return null;
    }
}
