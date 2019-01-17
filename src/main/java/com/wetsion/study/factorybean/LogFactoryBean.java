package com.wetsion.study.factorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @CLassName: LogFactoryBean
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/11 2:33 PM
 * @Version: 1.0
 */
public class LogFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    ApplicationContext applicationContext;

    Class<? extends IPrint> clz;

    List<IPrint> list;

    public LogFactoryBean(Class<? extends IPrint> clz) {
        this.clz = clz;
//        this.applicationContext = applicationContext;
//        Map<String, ? extends IPrint> map = this.applicationContext.getBeansOfType(clz);
//        list = new ArrayList<>(map.values());
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                for (IPrint spi : list) {
                    if (spi.verify(args[0])) {
                        // 第一个参数作为条件选择
                        return method.invoke(spi, args);
                    }
                }

                throw new Exception("no spi server can execute! spiList: " + list);
            }
        };
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz},
                invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return clz;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, ? extends IPrint> map = this.applicationContext.getBeansOfType(this.clz);
        this.list = new ArrayList<>(map.values());
    }
}
