package com.wetsion.study.multi_thread.cyclicbarrier;

import lombok.Data;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weixin
 * @version 1.0
 * @CLassName CyclicBarrierTest
 * @date 2019/8/23 2:59 PM
 */
@Data
public class CyclicBarrierTest {

    private int i = 0;

    public static void main1(String[] args) {
        ReentrantLock r0 = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final ReentrantLock r1 = r0;
                r1.lock();
                try {
                    System.out.println("r1");
                    for (;;) {}
                } finally {
                    System.out.println("aa");
                    r1.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ReentrantLock r2 = r0;
                r2.lock();
                try {
                    System.out.println("r2");
                    for (;;) {}
                } finally {
                    System.out.println("bb");
                    r2.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("铁柱彩礼钱借够了，结婚");
                System.out.println("铁柱老婆带着钱跟隔壁老王跑了");
            }
        });
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i=0;i< 9;i++){
            final int user = i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                        int k = cb.getNumberWaiting() + 1;
                        System.out.println("铁柱借钱凑彩礼，当前已有" + k + "百万");
                        cb.await(10000L, TimeUnit.MILLISECONDS);
                        System.out.println("铁柱卖血搬砖还钱");
                    } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(runnable);
        }
        threadPool.shutdown();
    }
}
