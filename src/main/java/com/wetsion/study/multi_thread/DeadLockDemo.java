package com.wetsion.study.multi_thread;

/**
 * 死锁例子
 *
 * @author weixin
 * @version 1.0
 * @CLassName DeadLockDemo
 * @date 2019/11/21 5:06 PM
 */
public class DeadLockDemo extends Thread {

    private String first;
    private String second;

    public DeadLockDemo(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String locaA = "lockA";
        String locaB = "lockB";
        DeadLockDemo deadLockDemo1 = new DeadLockDemo("Thread1", locaA, locaB);
        DeadLockDemo deadLockDemo2 = new DeadLockDemo("Thread2", locaB, locaA);
        deadLockDemo1.start();
        deadLockDemo2.start();
        deadLockDemo1.join();
        deadLockDemo2.join();
    }
}
