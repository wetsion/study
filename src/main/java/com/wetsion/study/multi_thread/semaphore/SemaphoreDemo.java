package com.wetsion.study.multi_thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wetsion on 2019/7/2.
 *
 * {@link Semaphore} 实践例子
 */
public class SemaphoreDemo {

    static class SemphoreRunner implements Runnable{
        private Semaphore semaphore;
        private int user;
        public SemphoreRunner(Semaphore semaphore, int user){
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

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0;i<20;i++){
            service.execute(new SemphoreRunner(semaphore,i+1));
        }
        service.shutdown();
    }
}
