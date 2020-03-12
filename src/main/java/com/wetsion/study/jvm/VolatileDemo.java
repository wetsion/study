package com.wetsion.study.jvm;

/**
 * @author weixin
 * @version 1.0
 * @CLassName VolatileDemo
 * @date 2020/3/10 3:10 PM
 */
public class VolatileDemo {

    private static volatile int i = 1;

    private static volatile int j = 2;

    public static void main(String[] args) {
        int a;

        a = i + j;

        i = i + 1;
        j = j + 1;

        String b = "a";
        synchronized (b) {
            System.out.println(b);
        }
    }
}
