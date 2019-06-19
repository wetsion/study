package com.wetsion.study.string;

/**
 * @author weixin
 * @version 1.0
 * @CLassName StaticSpanDemo
 * @date 2019/4/24 11:23 AM
 */
public class StaticSpanDemo {

    public static void main(String[] args) {
        new STest();
        System.out.println("--");
        new STest().call();
        System.out.println("--");
        STest.say();
        STest.say();
        //结果为:
        // a
        // hello
        // hello
    }

    static class STest {
        static {
            System.out.println("a");
        }

        public static void say() {
            System.out.println("hello");
        }

        public void call() {
            System.out.println("world");
        }
    }
}
