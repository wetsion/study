package com.wetsion.study.web;

import com.wetsion.study.factorybean.ILog;
import com.wetsion.study.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLassName: MyWebController
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/10 2:29 PM
 * @Version: 1.0
 */
@RestController
public class MyWebController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ILog log;

    @GetMapping("/test")
    public void test() {
        collectionService.getCollections();
        log.log(20, "aaa");
        log.log(10, "bbb");
    }
}
