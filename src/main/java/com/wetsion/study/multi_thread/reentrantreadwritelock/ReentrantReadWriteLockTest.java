package com.wetsion.study.multi_thread.reentrantreadwritelock;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * {@link java.util.concurrent.locks.ReentrantReadWriteLock} 学习
 *
 * Created by wetsion on 2019/8/29.
 */
@Slf4j
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        System.out.println(666666 & 65535);
        System.out.println(1 << 5);
        System.out.println(1 >> 5);
        System.out.println(1 >>> 5);

        Map<String, String> map = new HashMap<>(4);
        ReadWriteLock lock = new ReentrantReadWriteLock();

        for (int i = 0; i < 20; i++) {
            String key = String.valueOf(i % 4);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String value = null;
                    lock.readLock().lock();
                    try {
                        value = map.get(key);
                        if (value == null) {
                            lock.readLock().unlock();
                            lock.writeLock().lock();
                            try {
                                value = map.get(key);
                                if (value == null) {
                                    value = key + "-test";
                                    map.put(key, value);
                                    log.info(Thread.currentThread().getName() + "写value: " + value);
                                }
                                lock.readLock().lock();
                                log.info(Thread.currentThread().getName() + "获取到新value：" + value);
                            } finally {
                                lock.writeLock().unlock();
                            }
                        } else {
                            log.info(Thread.currentThread().getName() + "获取到了value: " + value);
                        }
                    } finally {
                        lock.readLock().unlock();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }

}
