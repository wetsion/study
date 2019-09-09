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
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
//        System.out.println(test.get("wx"));
//        System.out.println(test.get("wx"));
        for (int i = 0; i < 20; i++) {
            String key = String.valueOf(i % 4);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.get(key);
                }
            }).start();
        }
    }

    private Map<String, String> map = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public String get(String key) {
        String value = null;
        lock.readLock().lock();
        try {
            value = map.get(key);
            log.info("获取value");
            if (value == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "test";
                        map.put(key, value);
                        log.info("写value");
                    }
                    lock.readLock().lock();
                } finally {
                    lock.writeLock().unlock();
                }
//                lock.readLock().lock();
            }
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }

}
