package com.wetsion.study.my_pool;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weixin
 * @version 1.0
 * @CLassName MyConnection
 * @date 2019/7/15 11:47 AM
 */
@Slf4j
public class MyConnection {

    private String name;

    public MyConnection() {
        log.info("链接创建");
    }

    public void open() {
        log.info("链接打开");
    }

    public void close() {
        log.info("链接关闭");
    }

    public void setName(String name) {
        this.name = name;
    }
}
