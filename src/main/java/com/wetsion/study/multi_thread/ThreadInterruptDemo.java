package com.wetsion.study.multi_thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程中断
 *
 * @author weixin
 * @version 1.0
 * @CLassName ThreadInterruptDemo
 * @date 2020/3/4 2:08 PM
 */
@Slf4j
public class ThreadInterruptDemo {

    public static void main(String[] args) {
//        mainInterrupt();
        childInterrupt();
    }

    private static void mainInterrupt() {
        Thread.currentThread().interrupt();
        log.info("是否已停止：{}", Thread.interrupted());
        log.info("再次确认是否已停止：{}", Thread.interrupted());
    }

    private static void childInterrupt() {
        Thread t1 = new T1();
        t1.start();
        t1.interrupt();
        log.info("是否已停止：{}", t1.isInterrupted());
        log.info("再次确认是否已停止：{}", t1.isInterrupted());
    }

    private static void interrupted() {

    }

    static class T1 extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
}
