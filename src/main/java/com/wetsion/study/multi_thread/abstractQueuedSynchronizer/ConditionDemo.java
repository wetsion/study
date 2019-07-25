package com.wetsion.study.multi_thread.abstractQueuedSynchronizer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * aqs的Condition测试
 *
 *
 * Created by wetsion on 2019/7/22.
 */
public class ConditionDemo {

    static class Thread1 implements Runnable{
        private Lock lock;
        private Condition condition;
        public Thread1(Lock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }
        @Override
        public void run() {
            try {
                System.out.println("线程1争抢锁");
                lock.lock();
                System.out.println("线程1获得锁，调用await释放锁");
                condition.await();
                System.out.println("线程1被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    static class Thread2 implements Runnable{
        private Lock lock;
        private Condition condition;
        public Thread2(Lock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }
        @Override
        public void run() {
            try {
                System.out.println("线程2争抢锁");
                lock.lock();
                System.out.println("线程2获得锁, 调用await释放锁");
                condition.await();
                System.out.println("线程2被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    static class Thread3 implements Runnable{
        private Lock lock;
        private Condition condition;
        public Thread3(Lock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }
        @Override
        public void run() {
            try {
                System.out.println("线程3争抢锁");
                lock.lock();
                System.out.println("线程3获得锁");
                condition.signalAll();
                System.out.println("线程3唤醒条件队列等待线程");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread thread1 = new Thread(new Thread1(lock, condition));
        Thread thread2 = new Thread(new Thread2(lock, condition));
        Thread thread3 = new Thread(new Thread3(lock, condition));

        thread1.start();
        thread2.start();
        Thread.sleep(5000L);
        thread3.start();
    }
}
