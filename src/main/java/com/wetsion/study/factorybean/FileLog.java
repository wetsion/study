package com.wetsion.study.factorybean;

import org.springframework.stereotype.Component;

/**
 * @CLassName: FileLog
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/11 3:08 PM
 * @Version: 1.0
 */
@Component
public class FileLog implements ILog {
    @Override
    public void print(String msg) {
        System.out.println("file log:" + msg);
    }

    @Override
    public boolean verify(Integer condition) {
        return condition > 10;
    }
}
