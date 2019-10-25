package com.wetsion.study.self_def_config_center;

import lombok.Data;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SelfDefConfigValue
 * @date 2019/10/24 6:03 PM
 */
@Data
public class SelfDefConfigValue {

    private String beanName;
    private String key;
    private Field field;
    private WeakReference<Object> beanRef;

    public SelfDefConfigValue(String key, String beanName, Field field, Object bean) {
        this.beanName = beanName;
        this.key = key;
        this.field = field;
        this.beanRef = new WeakReference<>(bean);
    }

    public void update(Object newVal) throws IllegalAccessException {
        Object bean = beanRef.get();
        if (bean == null) {
            return;
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        field.set(bean, newVal);
        field.setAccessible(accessible);
    }
}
