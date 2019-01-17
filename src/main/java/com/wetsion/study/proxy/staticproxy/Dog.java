package com.wetsion.study.proxy.staticproxy;

import com.wetsion.study.proxy.IAnimal;

/**
 * @CLassName: Dog
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/17 8:12 PM
 * @Version: 1.0
 */
public class Dog implements IAnimal {
    @Override
    public void say() {
        System.out.println("汪汪汪");
    }
}
