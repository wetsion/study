package com.wetsion.study.rpcdiy.protocol;

import lombok.Data;

/**
 * @author weixin
 * @version 1.0
 * @CLassName RpcRequest
 * @date 2019/2/27 4:48 PM
 */
@Data
public class RpcRequest {

    //
    private String requestId;

    // 类名
    private String className;

    // 方法名
    private String methodName;

    // 所有请求参数的类型
    private Class<?>[] parameterTypes;

    // 所有的请求参数
    private Object[] parameters;
}
