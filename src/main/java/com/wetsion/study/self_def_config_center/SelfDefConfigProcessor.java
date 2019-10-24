package com.wetsion.study.self_def_config_center;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SelfDefConfigProcessor
 * @date 2019/10/24 5:45 PM
 */
public class SelfDefConfigProcessor implements BeanFactoryPostProcessor, BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        for (Field field : findAllField(clazz)) {
            processField(bean, beanName, field);
        }
        return bean;
    }

    private void processField(Object bean, String beanName, Field field) {
        Value value = field.getAnnotation(Value.class);
        if (value == null) {
            return;
        }
        String key = value.value();
        SelfDefConfigValue selfDefConfigValue = new SelfDefConfigValue(key, beanName, field, bean);
        SelfDefConfigValueRegistry.register(beanFactory, key, selfDefConfigValue);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private List<Field> findAllField(Class clazz) {
        final List<Field> fields = new LinkedList<>();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                fields.add(field);
            }
        });
        return fields;
    }
}
