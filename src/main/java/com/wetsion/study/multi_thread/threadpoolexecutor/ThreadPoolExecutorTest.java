package com.wetsion.study.multi_thread.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ThreadPoolExecutorTest
 * @date 2019/9/3 4:37 PM
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(1, 1,
                        0, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(1));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable("task-" + i));
        }
        executor.shutdown();
    }


    static class MyRunnable implements Runnable {

        private String name;

        MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.name + "is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
