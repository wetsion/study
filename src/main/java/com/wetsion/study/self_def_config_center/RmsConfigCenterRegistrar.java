package com.wetsion.study.self_def_config_center;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author weixin
 * @version 1.0
 * @CLassName RmsConfigCenterRegistrar
 * @date 2019/10/24 2:12 PM
 */
public class RmsConfigCenterRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry,
                SelfDefConfigProcessor.class.getName(), SelfDefConfigProcessor.class);

        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry,
                PropertySourceProcessor.class.getName(), PropertySourceProcessor.class);
    }
}
