package com.wetsion.study.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weixin
 * @version 1.0
 * @CLassName Template
 * @date 2019/5/8 12:55 PM
 */
public class Template<T> {

    public static <T> void test(T t) {
        Map map = new HashMap();
        map.put(t,t);
    }

    public static void main(String[] args) {
        Template.test("111");
    }
}
