package com.wetsion.study.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
