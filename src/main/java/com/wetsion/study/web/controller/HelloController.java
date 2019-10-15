package com.wetsion.study.web.controller;

import com.wetsion.study.web.config.RequestScopeBean;
import com.wetsion.study.web.config.ScopeService;
import com.wetsion.study.web.config.SessionScopeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weixin
 * @version 1.0
 * @CLassName HelloController
 * @date 2019/10/12 11:00 AM
 */
@RestController
public class HelloController {

    @Autowired
    RequestScopeBean requestScopeBean;

    @Autowired
    SessionScopeBean sessionScopeBean;

    @Autowired
    ScopeService scopeService;

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/hello-request")
    public void helloRequest() {
        System.out.println(requestScopeBean.getCurrentTime());
        scopeService.say();
    }

    @GetMapping("/hello-session")
    public void helloSession() {
//        System.out.println(sessionScopeBean.getRequestScopeBean().getCurrentTime());
        System.out.println(sessionScopeBean.getCurrentTime());
    }

    @GetMapping("/hello-kill")
    public void helloKill() {
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getParentBeanFactory();
        beanDefinitionRegistry.removeBeanDefinition("sessionScopeBean");
    }

    @GetMapping("/hello-e")
    public void helloE() {
        scopeService.printException();
    }
}
