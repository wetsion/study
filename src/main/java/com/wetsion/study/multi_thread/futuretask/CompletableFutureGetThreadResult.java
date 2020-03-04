package com.wetsion.study.multi_thread.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * 使用 {@link CompletableFuture} 获取线程执行结果
 *
 * @author weixin
 * @version 1.0
 * @CLassName CompletableFutureGetThreadResult
 * @date 2020/3/3 6:30 PM
 */
@Slf4j
public class CompletableFutureGetThreadResult {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        runAsyncDemo();
//        supplyAsyncDemo();
//        thenApplyDemo();
//        thenRunDemo();
//        thenHandleDemo();
//        thenAcceptDemo();
//        thenCombineDemo();
//        applyEitherDemo();
        thenComposeDemo();
    }

    private static void thenComposeDemo() throws InterruptedException, ExecutionException {
        log.info("开始时间：{}", System.currentTimeMillis());
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                try {
                    TimeUnit.SECONDS.sleep(5L);
                    log.info("任务1，时间: {}", System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1L;
            }
        });
        CompletableFuture<Long> rf = future1.thenCompose(new Function<Long, CompletionStage<Long>>() {
            @Override
            public CompletionStage<Long> apply(Long aLong) {
                return CompletableFuture.supplyAsync(new Supplier<Long>() {
                    @Override
                    public Long get() {
                        try {
                            TimeUnit.SECONDS.sleep(2L);
                            log.info("任务2，时间: {}", System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return aLong + 2L;
                    }
                });
            }
        });
        log.info("最终结果：{}", rf.get());
    }

    /**
     * applyToEither() 和 acceptEither() 都是两个前置任务哪个先完成就接收哪个的结果处理，
     * 但 applyToEither() 返回处理结果，acceptEither() 不返回结果；
     *
     * runAfterEither() 有点类似上面两个，是只要有一个前置任务完成就执行，但不接受前置任务结果，也不返回结果；
     *
     * runAfterBoth() 两个前置任务都完成才执行，也不接收前置任务结果，也不返回结果；
     **/
    private static void applyEitherDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                try {
                    TimeUnit.SECONDS.sleep(2L);
                    log.info("任务1，时间: {}", System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1L;
            }
        });
        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                try {
                    TimeUnit.SECONDS.sleep(10L);
                    log.info("任务2，时间: {}", System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 2L;
            }
        });
        CompletableFuture<Long> future3 = future1.applyToEither(future2, new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                log.info("先完成任务的结果：{}, 时间：{}", aLong, System.currentTimeMillis());
                return aLong;
            }
        });
        CompletableFuture<Void> future4 = future1.acceptEither(future2, new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                log.info("先完成任务的结果：{}, 时间：{}", aLong, System.currentTimeMillis());
            }
        });
        log.info("合并任务之后的结果：{}", future3.get());

        CompletableFuture<Void> future5 = future1.runAfterEither(future2, new Runnable() {
            @Override
            public void run() {
                log.info("有一个先完成了, 时间：{}", System.currentTimeMillis());
            }
        });
        CompletableFuture<Void> future6 = future1.runAfterBoth(future2, new Runnable() {
            @Override
            public void run() {
                log.info("两个都完成了, 时间：{}", System.currentTimeMillis());
            }
        });
        TimeUnit.SECONDS.sleep(15L);
    }

    /**
     * thenCombine() 用于合并两个任务的结果，并将最终结果返回；
     * thenAcceptBoth() 同 thenCombine()，但不返回最终结果
     **/
    private static void thenCombineDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                return 1L;
            }
        });
        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                return 2L;
            }
        });
        CompletableFuture<Long> future3 = future1.thenCombine(future2, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) {
                return aLong + aLong2;
            }
        });
        CompletableFuture<Void> future4 = future1.thenAcceptBoth(future2, new BiConsumer<Long, Long>() {
            @Override
            public void accept(Long aLong, Long aLong2) {
               log.info("合并之后的结果：{}", aLong + aLong2);
            }
        });
        log.info("合并任务之后的结果：{}", future3.get());
    }

    /**
     * accept() 同样也是接收前置任务的结果，
     * 并消费处理前置任务结果，但不返回结果，
     * 前置任务如果有异常，也将不会执行
     **/
    private static void thenAcceptDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                log.info("开始生成随机数，时间：{}", System.currentTimeMillis());
                Long r = ThreadLocalRandom.current().nextLong();
//                Long r = new Random().nextLong();
                log.info("已生成随机数，时间：{}", System.currentTimeMillis());
                log.info("随机数：{}", r);
                return r;
            }
        }).thenAccept(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                log.info("前置任务的结果：{}", aLong);
            }
        });
        TimeUnit.SECONDS.sleep(3L);
    }

    /**
     * 不同于下面的 thenSupply 和 thenRun，
     * handle()在前置任务出现异常后，依旧可以执行执行，并能处理异常，
     * 也消费前置任务结果，并返回结果
     **/
    private static void thenHandleDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                log.info("开始生成随机数，时间：{}", System.currentTimeMillis());
                Long r = ThreadLocalRandom.current().nextLong();
//                Long r = new Random().nextLong();
                log.info("已生成随机数，时间：{}", System.currentTimeMillis());
                log.info("随机数：{}", r);
                int i = 2/0; //构建异常
                return r;
            }
        }).handle(new BiFunction<Long, Throwable, Long>() {
            @Override
            public Long apply(Long aLong, Throwable throwable) {
                Long nr = 0L;
                log.info("新结果：{}", nr);
                if (throwable != null) {
                    log.info("异常：{}", throwable.getMessage());
                } else {
                    nr = aLong * 2;
                }
                return nr;
            }
        });
        TimeUnit.SECONDS.sleep(3L);
        Long r = future.get();
        log.info("最终打印结果：{}", r);
    }

    /**
     * thenApply()和thenRun() 只能执行正常的任务，前置任务出现异常后，后面的任务也不再执行，
     * thenRun() 不关心前置任务的结果，也不返回结果
     * thenApply() 消费前置任务的结果，返回结果
     **/
    private static void thenRunDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                log.info("开始生成随机数，时间：{}", System.currentTimeMillis());
                Long r = ThreadLocalRandom.current().nextLong();
//                Long r = new Random().nextLong();
                log.info("已生成随机数，时间：{}", System.currentTimeMillis());
                log.info("随机数：{}", r);
                int i = 2/0; // 构建异常
                return r;
            }
        }).thenRun(new Runnable() {
            @Override
            public void run() {
                log.info("新结果：{}");
            }
        });
        TimeUnit.SECONDS.sleep(3L);
    }

    private static void thenApplyDemo() throws InterruptedException, ExecutionException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                log.info("开始生成随机数，时间：{}", System.currentTimeMillis());
                Long r = ThreadLocalRandom.current().nextLong();
//                Long r = new Random().nextLong();
                log.info("已生成随机数，时间：{}", System.currentTimeMillis());
                log.info("随机数：{}", r);
                int i = 2/0; //构建异常
                return r;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                Long nr = aLong * 2;
                log.info("新结果：{}", nr);
                return nr;
            }
        });
        TimeUnit.SECONDS.sleep(3L);
        Long r = future.get();
        log.info("最终打印结果：{}", r);
    }

    private static void supplyAsyncDemo() throws InterruptedException, ExecutionException {
        log.info("时间：{}", System.currentTimeMillis());
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 10;
            }
        });

        completableFuture.whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                log.info("时间：{}, 完成的结果：{}", System.currentTimeMillis(), integer);
            }
        });

        TimeUnit.SECONDS.sleep(4L);
//        Integer r = completableFuture.get();
//        log.info("时间：{}, result: {}", System.currentTimeMillis(), r);
    }

    private static void runAsyncDemo() throws InterruptedException, ExecutionException {
        log.info("时间：{}", System.currentTimeMillis());
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                log.info("run async");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("run async end");
            }
        });

        completableFuture.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                log.info("完成");
            }
        });

        completableFuture.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2L);
//        completableFuture.get();
//        log.info("时间：{}, result: {}", System.currentTimeMillis(), r);
    }
}
