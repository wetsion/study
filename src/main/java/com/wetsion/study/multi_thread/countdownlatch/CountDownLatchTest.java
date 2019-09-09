package com.wetsion.study.multi_thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author weixin
 * @version 1.0
 * @CLassName CountDownLatchTest
 * @date 2019/8/21 4:44 PM
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("正在攒钱买房...");
                    Thread.sleep((long) (Math.random()*10000));
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("正在攒钱买车...");
                    Thread.sleep((long) (Math.random()*10000));
                    latch.countDown();
                    System.out.println("还有" + latch.getCount() + "个目标没完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //主线程
        System.out.println("等待有车有房后就结婚");
        try {
            //等待，阻塞点
            latch.await();
            System.out.println("已经买车买房了，准备结婚了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
