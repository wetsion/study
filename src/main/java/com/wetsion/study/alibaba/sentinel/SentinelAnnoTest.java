package com.wetsion.study.alibaba.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SentinelAnnoTest
 * @date 2019/4/10 2:30 PM
 */
public class SentinelAnnoTest {

    @SentinelResource(blockHandler = "blockHandlerForSay")
    public void say() {
        throw new RuntimeException("failed");
    }

    public void blockHandlerForSay(BlockException e) {
        System.out.println("降级处理");
        System.out.println(e.getMessage());
    }
}
