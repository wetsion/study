package com.wetsion.study.multi_thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AsyncServiceTest
 * @date 2019/4/17 4:33 PM
 */
@Service
public class AsyncServiceTest {

    @Async
    public Future<String> asyncCall(int i) {
        Long time = System.currentTimeMillis();
        System.out.println("c: "+i+",thread:" + Thread.currentThread().getName() + ", time: " + time);
        return new AsyncResult<>("" + time);
    }
}
