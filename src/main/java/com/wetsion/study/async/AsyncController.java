package com.wetsion.study.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AsyncController
 * @date 2019/6/12 10:46 AM
 */
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/async/test2")
    public void testAsync() {
        asyncService.test();
    }

    @GetMapping("/async/test3")
    public void testResultAsync() throws ExecutionException, InterruptedException {
        Future<String> future = asyncService.testFuture();
        asyncService.test();
        System.out.println(future.get());
    }
}
