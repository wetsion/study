package com.wetsion.study.multi_thread.threadpoolexecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 使用{@link ExecutorService} 获取线程执行结果
 *
 * @author weixin
 * @version 1.0
 * @CLassName ExecutorServiceGetThreadResult
 * @date 2020/3/3 6:10 PM
 */
@Slf4j
public class ExecutorServiceGetThreadResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return 10;
            }
        });

        executorService.shutdown();

        log.info("result: {}", future.get());
    }
}
