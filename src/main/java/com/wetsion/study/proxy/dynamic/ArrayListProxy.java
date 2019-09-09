package com.wetsion.study.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ArrayListProxy
 * @date 2019/8/8 11:33 AM
 */
public class ArrayListProxy {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        List proxy = (List) Proxy.newProxyInstance(List.class.getClassLoader(),
                ArrayList.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(list, args);
                    }
                });

        proxy.add("aaa");
        proxy.add("bbb");
    }
}
