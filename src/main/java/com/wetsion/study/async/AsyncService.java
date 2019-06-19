package com.wetsion.study.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AsyncService
 * @date 2019/6/12 10:46 AM
 */
@Service
@Slf4j
public class AsyncService {

    /**
     * 使用指定的自定义的线程池
     **/
    @Async("mySimpleAsync")
    public void test() {
        log.info("test:" + Thread.currentThread());
    }
}
