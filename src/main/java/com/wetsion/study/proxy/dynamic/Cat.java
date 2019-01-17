package com.wetsion.study.proxy.dynamic;

import com.wetsion.study.proxy.IAnimal;

/**
 * @CLassName: Cat
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/17 8:16 PM
 * @Version: 1.0
 */
public class Cat implements IAnimal {
    @Override
    public void say() {
        System.out.println("喵喵喵");
    }
}
