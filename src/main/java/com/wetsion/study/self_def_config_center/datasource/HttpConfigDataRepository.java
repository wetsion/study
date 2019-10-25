package com.wetsion.study.self_def_config_center.datasource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wetsion.study.self_def_config_center.ConfigDataUtil;
import com.wetsion.study.self_def_config_center.PlatformBean;
import com.wetsion.study.self_def_config_center.PlatformConfigDataCache;
import com.wetsion.study.self_def_config_center.config.ConfigDataChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wetsion on 2019/10/25.
 */
@Slf4j
public class HttpConfigDataRepository implements ConfigDataRepository {

//    private volatile AtomicReference<PlatformConfigDataCache> platformConfigDataCache;
    private volatile AtomicReference<PlatformConfigDataCache> platformConfigDataCache;

    private ConfigDataChangeListener listener;

    private PlatformBean platformBean;

    private final static ScheduledExecutorService executorService;

    static {
        executorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "Rms_Http_Config_Data_Repository_Thread-" + System.currentTimeMillis());
                thread.setDaemon(true);
                if (thread.getPriority() != Thread.NORM_PRIORITY) {
                    thread.setPriority(Thread.NORM_PRIORITY);
                }
                return thread;
            }
        });
    }

    public HttpConfigDataRepository(ConfigDataChangeListener listener, PlatformBean platformBean) {
        this.platformConfigDataCache = new AtomicReference<>();
        this.platformBean = platformBean;
        this.addChangeListener(listener);
        this.syncData();
        this.scheduleRefreshData();
    }

    @Override
    public synchronized void syncData() {
        // 同步获取数据，如果变化就调用listener
        RestTemplate restTemplate = new RestTemplate();
        try {
            String result = restTemplate.getForObject(platformBean.getConfigUrl() + "?clientId=" + platformBean.getClientId(),
                    String.class);
            if (Objects.nonNull(result)) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                log.info(jsonObject.toJSONString());
                if (jsonObject.getBooleanValue("success")) {
                    Properties properties = ConfigDataUtil.trasformConfigData(
                            jsonObject.getJSONObject("results").getString("configs"));
                    PlatformConfigDataCache current =
                            new PlatformConfigDataCache();
                    current.setClientId(platformBean.getClientId());
                    current.setConfigurations(properties);
                    current.setLastUpdateTime(
                            jsonObject.getJSONObject("results").getLong("updateTime"));

                    PlatformConfigDataCache previous = this.platformConfigDataCache.get();
                    if (Objects.isNull(previous)) {
                        this.platformConfigDataCache.set(current);
                        log.debug("after set cache: {}", JSON.toJSONString(this.platformConfigDataCache.get()));
                    } else {
                        if (current.getLastUpdateTime() > previous.getLastUpdateTime()) {
                            this.platformConfigDataCache.set(current);
                            listener.onChange(current);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取配置数据失败", e);
        }
    }

    @Override
    public void scheduleRefreshData() {
        // 周期性调用syncData()
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("start refresh config data");
                syncData();
            }
        }, 20, 10, TimeUnit.SECONDS);
    }

    @Override
    public void addChangeListener(ConfigDataChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public PlatformConfigDataCache getPlatformConfigDataCache() {
        return this.platformConfigDataCache.get();
    }
}
