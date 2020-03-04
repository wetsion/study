package com.wetsion.study.multi_thread.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 使用 {@link FutureTask} 获取线程执行结果
 *
 * @author weixin
 * @version 1.0
 * @CLassName FutureTaskGetThreadResult
 * @date 2020/3/3 6:17 PM
 */
@Slf4j
public class FutureTaskGetThreadResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("时间：{}", System.currentTimeMillis());
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return 10;
            }
        });

        new Thread(futureTask).start();
        Integer r = futureTask.get();
        log.info("时间：{}, result: {}", System.currentTimeMillis(), r);
    }
}
