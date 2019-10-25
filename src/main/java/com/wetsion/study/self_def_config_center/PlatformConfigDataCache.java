package com.wetsion.study.self_def_config_center;

import lombok.Data;

import java.util.Map;
import java.util.Properties;

/**
 * 本地缓存以及接收绑定数据
 *
 * @author weixin
 * @version 1.0
 * @CLassName PlatformConfigDataCache
 * @date 2019/10/24 8:39 PM
 */
@Data
public class PlatformConfigDataCache {

    private String clientId;

    private Properties configurations;

    private Long lastUpdateTime;
}
