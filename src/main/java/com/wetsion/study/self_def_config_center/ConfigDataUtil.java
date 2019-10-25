package com.wetsion.study.self_def_config_center;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ConfigDataUtil
 * @date 2019/10/25 1:34 PM
 */
@Slf4j
public class ConfigDataUtil {

    public static Properties trasformConfigData(String text) {
        Properties properties = new Properties();
        if (StringUtils.isEmpty(text)) {
            return properties;
        }
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            log.error("读取文本创建配置项失败", e);
        }
        return properties;
    }
}
