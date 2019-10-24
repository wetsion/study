package com.wetsion.study.self_def_config_center;

import org.springframework.beans.factory.BeanFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SelfDefConfigValueRegistry
 * @date 2019/10/24 6:10 PM
 */
public class SelfDefConfigValueRegistry {

    private static final Map<BeanFactory, Map<String, List<SelfDefConfigValue>>> registry = new ConcurrentHashMap<>();

    private static final Object LOCK = new Object();

    public static void register(BeanFactory beanFactory, String key, SelfDefConfigValue selfDefConfigValue) {
        if (!registry.containsKey(beanFactory)) {
            synchronized (LOCK) {
                if (!registry.containsKey(beanFactory)) {
                    registry.put(beanFactory, new HashMap<String, List<SelfDefConfigValue>>());
                }
            }
        }
        List<SelfDefConfigValue> list = registry.get(beanFactory).get(key);
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }
        list.add(selfDefConfigValue);
        registry.get(beanFactory).put(key, list);
    }

    public static Map<BeanFactory, Map<String, List<SelfDefConfigValue>>> getRegistry() {
        return registry;
    }
}
