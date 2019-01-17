package com.wetsion.study.proxy.staticproxy;

import com.wetsion.study.proxy.IAnimal;

/**
 * @CLassName: ProxyDog
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/17 8:13 PM
 * @Version: 1.0
 */
public class ProxyDog implements IAnimal {

    private IAnimal dog;

    public ProxyDog(IAnimal dog) {
        super();
        this.dog = dog;
    }

    @Override
    public void say() {
        dog.say();
    }
}
