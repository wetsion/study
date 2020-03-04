package com.wetsion.study.multi_thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 *
 * LongAdder 在并发情况递增
 *
 * @author weixin
 * @version 1.0
 * @CLassName LongAdderDemo
 * @date 2020/3/3 4:43 PM
 */
public class LongAdderDemo {

    private static LongAdder longAdder = new LongAdder();

    private static LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
        @Override
        public long applyAsLong(long left, long right) {
            return left + right;
        }
    }, 0L);

    public static void add() {
        longAdder.increment();
        longAccumulator.accumulate(1L);
    }

    public static void reset() {
        longAdder.reset();
        longAccumulator.reset();
    }

    public static long r() {
//        return longAdder.sum();
        return longAccumulator.longValue();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            reset();
            CountDownLatch latch = new CountDownLatch(50);
            for (int j = 0; j < 50; j++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        add();
                        latch.countDown();
                    }
                }).start();
            }
            System.out.println(i + "在等待");
            latch.await();
            System.out.println("结果：" + r());
        }
    }
}
