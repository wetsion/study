package com.wetsion.study.proxy.dynamic;

import com.wetsion.study.proxy.IAnimal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @CLassName: DynamicCat
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/17 8:17 PM
 * @Version: 1.0
 */
public class DynamicCat implements InvocationHandler {

    private IAnimal cat;

    public IAnimal builder(IAnimal cat) {
        this.cat = cat;
        return (IAnimal) Proxy.newProxyInstance(cat.getClass().getClassLoader(),
                this.cat.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.cat, args);
    }
}
