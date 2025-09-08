package com.wetsion.study.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        new Thread(() -> {
            lock1.lock();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread1 lock1");
            lock2.lock();
            lock2.unlock();
            lock1.unlock();
        }).start();
        new Thread(() -> {
            lock2.lock();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread2 lock2");
            lock1.lock();
            lock1.unlock();
            lock2.unlock();
        }).start();
    }
}
