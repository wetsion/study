package com.wetsion.study.javacore;

/**
 * @author weixin
 * @version 1.0
 * @CLassName StaticNoun
 * @date 2019/10/30 3:50 PM
 */
public class StaticNoun {

    public static String A;

    static {
        int b = 1/0;
        A = "b";
    }
}
