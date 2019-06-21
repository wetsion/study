package com.wetsion.study.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AsyncController
 * @date 2019/6/12 10:46 AM
 */
@RestController
@Slf4j
public class AsyncController {

    private List<Future<String>> futures = new ArrayList<>();

    @Autowired
    AsyncService asyncService;

    @GetMapping("/async/test2")
    public void testAsync() {
        asyncService.test();
    }

    @GetMapping("/async/test3")
    public void testResultAsync() throws ExecutionException, InterruptedException {
        Future<String> future = asyncService.testFuture();
        futures.add(future);
        asyncService.test();
//        System.out.println(future.get());
    }

    @GetMapping("/async/test3/get")
    public void testResultAsyncGet() {
        futures.forEach(f -> {
            try {
                log.info(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
