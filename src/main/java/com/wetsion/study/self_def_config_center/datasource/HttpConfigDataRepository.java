package com.wetsion.study.self_def_config_center.datasource;

import com.wetsion.study.self_def_config_center.PlatformConfigDataCache;
import com.wetsion.study.self_def_config_center.config.ConfigDataChangeListener;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wetsion on 2019/10/25.
 */
public class HttpConfigDataRepository implements ConfigDataRepository {

    private volatile AtomicReference<PlatformConfigDataCache> platformConfigDataCache;

    private ConfigDataChangeListener listener;

    public HttpConfigDataRepository() {
        this.syncData();
        this.scheduleRefreshData();
    }

    public void syncData() {
        // 同步获取数据，如果变化就调用listener
        platformConfigDataCache.set(new PlatformConfigDataCache());
        listener.onChange();
    }

    public void scheduleRefreshData() {
        // 周期性调用syncData()
        this.syncData();
    }

    @Override
    public void addChangeListener(ConfigDataChangeListener listener) {
        this.listener = listener;
    }
}
