package com.wetsion.study.guava;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weixin
 * @version 1.0
 * @CLassName GuavaDemo2
 * @date 2020/3/3 11:36 AM
 */
@Slf4j
public class GuavaDemo2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {

            ListeningExecutorService listeningExecutorService =
                    MoreExecutors.listeningDecorator(executorService);

            List<ListenableFuture<Integer>> futures = new ArrayList<>();

            for (int i = 0; i < 10; i++) {

                ListenableFuture<Integer> submit = listeningExecutorService.submit(new GuavaDemo1.NumberCallable(i));

                submit.addListener(() -> {
                    log.info("任务执行完，被回调");
                }, MoreExecutors.directExecutor());

                futures.add(submit);
            }

            List<Integer> results = Futures.allAsList(futures).get();

            results.forEach(item -> {
                log.info("{}", item);
            });

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
