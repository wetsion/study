package com.wetsion.study.classloader;

import com.wetsion.study.proxy.DynamicRun;

/**
 * @author weixin
 * @version 1.0
 * @CLassName MyClassLoader
 * @date 2020/3/9 6:28 PM
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] bytes) {
        return defineClass(name, bytes, 0, bytes.length);
    }

}
