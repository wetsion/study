package com.wetsion.study.multi_thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author weixin
 * @version 1.0
 * @CLassName AsyncControllerTest
 * @date 2019/4/17 4:33 PM
 */
@RestController
public class AsyncControllerTest {

    @Autowired
    AsyncServiceTest asyncServiceTest;

    @GetMapping("/async/test")
    public void test1() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            Future<String> future = asyncServiceTest.asyncCall(i);
            if (future.isDone()) {
                System.out.println(future.get());
            } else {
                System.out.println("doing:" + i);
            }
//            System.out.println(future.get());
        }
    }
}
