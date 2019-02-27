package com.wetsion.study.rpcdiy.protocol;

import lombok.Data;

/**
 * @author weixin
 * @version 1.0
 * @CLassName RpcResponse
 * @date 2019/2/27 4:57 PM
 */
@Data
public class RpcResponse {

    private String requestId;

    // 抛出的异常
    private Throwable throwable;

    // 返回的结果
    private Object result;
}
