package com.wetsion.study.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weixin
 * @version 1.0
 * @CLassName TransactionTestEndpoint
 * @date 2019/4/4 5:20 PM
 */
@RestController
public class TransactionTestEndpoint {

//    @Autowired
//    ParentService parentService;

    @Autowired
    ChildService childService;

    @GetMapping("/trans/test")
    public void test() {
//        parentService.test();
//        childService.test();
        childService.childTest();
    }
}
