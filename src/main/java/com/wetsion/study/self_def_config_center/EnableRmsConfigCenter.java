package com.wetsion.study.self_def_config_center;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author weixin
 * @version 1.0
 * @CLassName EnableRmsConfigCenter
 * @date 2019/10/24 2:10 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RmsConfigCenterRegistrar.class)
public @interface EnableRmsConfigCenter {

    String type() default "redis";
}
