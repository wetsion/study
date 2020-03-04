package com.wetsion.study.multi_thread.countdownlatch;

import com.wetsion.study.multi_thread.ThreadJoinResult;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch获取线程执行结果
 *
 * @author weixin
 * @version 1.0
 * @CLassName GetThreadResult
 * @date 2020/3/3 5:59 PM
 */
@Slf4j
public class GetThreadResult {

    public static void main(String[] args) throws InterruptedException {
        log.info("当前时间：{}", System.currentTimeMillis());

        CountDownLatch latch = new CountDownLatch(1);

        ThreadJoinResult.Result<Integer> result = new ThreadJoinResult.Result<>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    result.setResult(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        t.start();
        latch.await();
        log.info("结束时间: {}, 结果: {}", System.currentTimeMillis(), result.getResult());
    }
}
