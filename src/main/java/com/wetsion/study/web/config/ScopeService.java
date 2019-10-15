package com.wetsion.study.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ScopeService
 * @date 2019/10/15 4:57 PM
 */
@Service
@Slf4j
public class ScopeService {

    @Autowired
    RequestScopeBean requestScopeBean;

    public void say() {
        log.info("current request: {}", requestScopeBean.getCurrentTime());
    }

    public void printException() {
        try {
            int i = 10/0;
        } catch (Exception e) {
            log.error("异常 {}", e.getMessage(), e);
        }
    }
}
