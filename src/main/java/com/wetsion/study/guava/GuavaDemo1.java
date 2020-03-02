package com.wetsion.study.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author weixin
 * @version 1.0
 * @CLassName Demo1
 * @date 2020/3/2 6:56 PM
 */
@Slf4j
public class GuavaDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {

            ListeningExecutorService listeningExecutorService =
                    MoreExecutors.listeningDecorator(executorService);

            for (int i = 0; i < 10; i++) {

                ListenableFuture<Integer> submit = listeningExecutorService.submit(new NumberCallable(i));

                submit.addListener(() -> {
                    log.info("任务执行完，被回调");
                }, MoreExecutors.directExecutor());

                log.info("{}", submit.get());
                log.info("{}", submit.get());
            }

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }

    static class NumberCallable implements Callable<Integer> {

        private int i;

        public NumberCallable(int i) {
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            log.info("{}", System.currentTimeMillis());
//            TimeUnit.SECONDS.sleep(2);
            log.info("{}", System.currentTimeMillis());
            return i;
        }
    }
}
