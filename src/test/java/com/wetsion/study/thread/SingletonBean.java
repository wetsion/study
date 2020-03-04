package com.wetsion.study.thread;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SingletonBean
 * @date 2019/11/19 3:17 PM
 */
public class SingletonBean {

    public void print() {
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SingletonBean bean = new SingletonBean();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bean.print();
                }
            }).start();
        }
    }
}
