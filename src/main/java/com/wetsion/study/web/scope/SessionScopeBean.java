package com.wetsion.study.web.scope;

import lombok.Data;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SessionScopeBean
 * @date 2019/10/12 10:57 AM
 */
@Data
public class SessionScopeBean {

    RequestScopeBean requestScopeBean;

    Long requestTime;

    Long currentTime;
}
