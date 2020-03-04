package com.wetsion.study.multi_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 通过Thread.join() 获取线程执行结果
 *
 * @author weixin
 * @version 1.0
 * @CLassName ThreadJoinResult
 * @date 2020/3/3 5:30 PM
 */
@Slf4j
public class ThreadJoinResult {

    public static class Result<T> {
        T result;

        public void setResult(T result) {
            this.result = result;
        }

        public T getResult() {
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("当前时间：{}", System.currentTimeMillis());
        Result<Integer> result = new Result<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    result.setResult(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join(); // 让主现场等待t线程执行完毕再继续，join()会让当前线程阻塞
        log.info("结束时间: {}, 结果: {}", System.currentTimeMillis(), result.getResult());
    }
}
