package com.wetsion.study.multi_thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weixin
 * @version 1.0
 * @CLassName LockDemo2
 * @date 2020/3/3 2:28 PM
 */
@Slf4j
public class LockDemo2 {

    public static void main(String[] args) throws InterruptedException {
        test1(false);
        TimeUnit.SECONDS.sleep(4);
        log.info("=====================");
        test1(true);
    }

    private static void test1(boolean fair) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(fair);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                log.info("t1 start");
                try {
                    TimeUnit.SECONDS.sleep(3);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            cm(lock, "son");
                        }
                    }).start();
                    log.info("t1 end");
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                } finally {
                    lock.unlock();
                }
            }
        });
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        cm(lock, "father");
    }

    private static void cm(ReentrantLock lock, String pre) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        log.info("子获取到锁");
                    } finally {
                        lock.unlock();
                    }
                }
            });
            t.setName(pre + "-" + i);
            t.start();
        }
    }
}
