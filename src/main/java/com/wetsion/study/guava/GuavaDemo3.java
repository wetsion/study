package com.wetsion.study.guava;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weixin
 * @version 1.0
 * @CLassName GuavaDemo3
 * @date 2020/3/3 2:01 PM
 */
@Slf4j
public class GuavaDemo3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        try {

            ListeningExecutorService listeningExecutorService =
                    MoreExecutors.listeningDecorator(executorService);

            List<ListenableFuture<Integer>> futures = new ArrayList<>();

            for (int i = 0; i < 10; i++) {

                ListenableFuture<Integer> future = listeningExecutorService.submit(new GuavaDemo1.NumberCallable(i));

                Futures.addCallback(future, new FutureCallback<Integer>() {
                    @Override
                    public void onSuccess(@Nullable Integer result) {
                        log.info("执行成功：{}", result);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        log.error("执行发生异常:{}", t.getMessage());
                    }
                }, MoreExecutors.directExecutor());

                futures.add(future);
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
