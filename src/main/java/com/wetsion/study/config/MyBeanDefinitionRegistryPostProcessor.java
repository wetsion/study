package com.wetsion.study.config;

import com.wetsion.study.service.CollectionService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @CLassName: MyBeanDefinitionRegistryPostProcessor
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/10 2:14 PM
 * @Version: 1.0
 */
@Configuration
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinition beanDefinition = null;
        if (!registry.containsBeanDefinition("collectionService")) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(new CollectionService() {
                @Override
                public void getCollections() {
                    System.out.println("new collections");
                }
            }.getClass());
            beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
            registry.registerBeanDefinition("collectionService", beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
