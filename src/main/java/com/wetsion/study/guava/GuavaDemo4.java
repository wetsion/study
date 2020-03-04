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
 * @CLassName GuavaDemo4
 * @date 2020/3/3 2:04 PM
 */
@Slf4j
public class GuavaDemo4 {

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

            ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(futures);
            Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
                @Override
                public void onSuccess(@Nullable List<Integer> result) {
                    log.info("所有结果之和：{}", result.stream().reduce(Integer::sum).get());
                }

                @Override
                public void onFailure(Throwable t) {
                    log.error("执行发生异常：{}", t.getMessage());
                }
            }, MoreExecutors.directExecutor());

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}
