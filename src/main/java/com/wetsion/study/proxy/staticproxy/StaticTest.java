package com.wetsion.study.proxy.staticproxy;

import com.wetsion.study.proxy.IAnimal;

/**
 * 静态代理测试
 *
 * @CLassName: StaticTest
 * @Author: weixin
 * @DATE: 2019/1/17 8:15 PM
 * @Version: 1.0
 */
public class StaticTest {
    public static void main(String[] args) {
        IAnimal proxyDog = new ProxyDog(new Dog());
        proxyDog.say();
    }
}
