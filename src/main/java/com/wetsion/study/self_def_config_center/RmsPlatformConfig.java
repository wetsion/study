package com.wetsion.study.self_def_config_center;

import lombok.Data;

import java.util.Map;

/**
 * @author weixin
 * @version 1.0
 * @CLassName RmsPlatformConfig
 * @date 2019/10/24 8:39 PM
 */
@Data
public class RmsPlatformConfig {

    private String clientId;

    private Map<String, String> configurations;

    private Long lastUpdateTime;
}
