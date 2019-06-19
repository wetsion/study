package com.wetsion.study.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weixin
 * @version 1.0
 * @CLassName FinalTest
 * @date 2019/4/12 5:35 PM
 */
public class FinalTest {

    private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        set();
        get();
    }

    private static void set() {
        map.put("a", "a");
    }

    private static void get() {
        System.out.println(map.get("a"));
    }
}
