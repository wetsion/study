package com.wetsion.study.multi_thread.volatile_test;

import org.junit.Test;

/**
 * @author weixin
 * @version 1.0
 * @CLassName VolatileTest
 * @date 2019/6/24 5:34 PM
 */
public class VolatileTest {

    private volatile int count = 0;

    @Test
    public void volatileAdd() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count ++;
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(3000L);
        System.out.println(count);
    }
}
