package com.wetsion.study.multi_thread.self_threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author weixin
 * @version 1.0
 * @CLassName MineThreadPool
 * @date 2019/7/2 6:00 PM
 */
public class MineThreadPool {

    public MineThreadPool(int minSize,
                          int maxSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue) {

    }


    private final class Worker extends Thread {

        private Runnable task;

        private Thread thread;

        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        @Override
        public void run() {
            super.run();
        }
    }
}
