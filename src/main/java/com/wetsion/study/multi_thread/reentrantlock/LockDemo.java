package com.wetsion.study.multi_thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weixin
 * @version 1.0
 * @CLassName LockDemo
 * @date 2019/7/3 7:30 PM
 */
public class LockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " aaa");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
    }



}
