package com.wetsion.study.service;

//import feign.Body;
//import feign.Headers;
//import feign.Param;
//import feign.RequestLine;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @CLassName: CollectionService
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2018/9/25 下午5:51
 * @Version: 1.0
 */
//@FeignClient(value = "collection-service")
public interface CollectionService {

//    @GetMapping("/collections/list")
//    @RequestLine("POST /user/login")
//    @Headers("Content-Type: application/json")
//    @Body("%7B\"username\": \"{username}\", \"password\": \"{password}\"%7D")
//    String login(@Param("username") String username, @Param("password") String password);
    void getCollections();
}
