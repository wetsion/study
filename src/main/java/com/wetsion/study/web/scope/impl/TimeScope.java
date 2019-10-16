package com.wetsion.study.web.scope.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义时间作用域，同一分钟相同bean
 *
 * @author weixin
 * @version 1.0
 * @CLassName TimeScope
 * @date 2019/10/16 11:59 AM
 */
@Slf4j
public class TimeScope implements Scope {

    private static Map<String, Map<Integer, Object>> scopeBeanMap = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Integer hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        // 当前是一天内的第多少分钟
        Integer minute = hour * 60 + Calendar.getInstance().get(Calendar.MINUTE);
        log.info("当前是第 {} 分钟", minute);
        Map<Integer, Object> objectMap = scopeBeanMap.get(name);
        Object object = null;
        if (Objects.isNull(objectMap)) {
            objectMap = new HashMap<>();
            object = objectFactory.getObject();
            objectMap.put(minute, object);
            scopeBeanMap.put(name, objectMap);
        } else {
            object = objectMap.get(minute);
            if (Objects.isNull(object)) {
                object = objectFactory.getObject();
                objectMap.put(minute, object);
                scopeBeanMap.put(name, objectMap);
            }
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        return scopeBeanMap.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
