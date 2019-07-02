package com.wetsion.study.multi_thread.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wetsion on 2019/7/2.
 *
 * 使用{@link java.util.concurrent.locks.ReentrantLock}
 * 实现信号量 {@link java.util.concurrent.Semaphore}
 */
public class ReentrantlockSemaphore {

    private final ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private int permits;

    public ReentrantlockSemaphore(int initPermit) {
        lock.lock();
        permits = initPermit;
        lock.unlock();
    }

    public void acquire() {
        lock.lock();
        try {
            while (permits <= 0) {
                condition.await();
            }
            --permits;
        } catch (InterruptedException e) {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            if (permits >=2) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantlockSemaphore semaphore = new ReentrantlockSemaphore(2);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i< 20; i++){
            service.execute(new SemphoreRunner(semaphore,i+1));
        }
//        service.shutdown();
    }

    static class SemphoreRunner implements Runnable{
        private ReentrantlockSemaphore semaphore;
        private int user;
        public SemphoreRunner(ReentrantlockSemaphore semaphore, int user){
            this.semaphore = semaphore;
            this.user = user;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("用户："+user+"进入窗口，准备买票");
                Thread.sleep((long) (Math.random()*3000));
                System.out.println("用户："+user+"买好票，准备离开");
                Thread.sleep((long) (Math.random()*3000));
                System.out.println("用户："+user+"已经离开");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

}
