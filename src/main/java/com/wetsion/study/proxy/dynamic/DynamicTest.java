package com.wetsion.study.proxy.dynamic;

import com.wetsion.study.proxy.IAnimal;

/**
 * @CLassName: DynamicTest
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/17 8:22 PM
 * @Version: 1.0
 */
public class DynamicTest {
    public static void main(String[] args) {
        IAnimal proxyCat = new DynamicCat().builder(new Cat());
        proxyCat.say();
    }
}
