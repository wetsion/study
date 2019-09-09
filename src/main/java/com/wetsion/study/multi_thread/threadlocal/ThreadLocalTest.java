package com.wetsion.study.multi_thread.threadlocal;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ThreadLocalTest
 * @date 2019/8/15 5:42 PM
 */
public class ThreadLocalTest {

    public final static ThreadLocal<String> tl = new ThreadLocal<>();

    private static boolean t1() {
        System.out.println("t1");
        return true;
    }

    public static void main(String[] args) {
        int i = 1;
        if (i > 0 || t1()) {
            System.out.println("t2");
        }
        ThreadLocalTest.tl.set("aa");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocalTest.tl.set("bb");
                new ThreadLocalTest().get();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocalTest.tl.set("cc");
                new ThreadLocalTest().get();
            }
        }).start();

        /*
         * 输出结果：
         * bb
         * cc
         *
         **/
    }

    private void get() {
        System.out.println(tl.get());
    }
}
