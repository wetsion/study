package com.wetsion.study.lombok;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author weixin
 * @version 1.0
 * @CLassName LombokDemo
 * @date 2019/10/14 4:11 PM
 */
@Slf4j
public class LombokDemo {

    public static void main(String[] args) {
        LombokBean lombokBean = LombokBean.builder()
                .name("wx")
                .note("aaa")
                .time(System.currentTimeMillis())
                .build();
        log.info(JSON.toJSONString(lombokBean));

        lombokBean = lombokBean.toBuilder()
                .name("sj")
                .note("bb")
                .build();

        log.info(JSON.toJSONString(lombokBean));
    }
}
