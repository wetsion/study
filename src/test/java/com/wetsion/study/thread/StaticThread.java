package com.wetsion.study.thread;

/**
 * @CLassName: StaticThread
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/14 2:28 PM
 * @Version: 1.0
 */
public class StaticThread implements Runnable {

    private int i = 0;

    public static void print(int i) {
        System.out.println(Thread.currentThread().getName() + ":" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(new StaticThread()).start();
        }
    }

    @Override
    public void run() {
        i++;
        print(i);
    }
}
