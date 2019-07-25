package com.wetsion.study.web.exception;

import com.wetsion.study.web.res.BaseResponse;
import com.wetsion.study.web.res.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * rest 异常统一处理
 *
 * Created by wetsion on 2019/7/25.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e) {
        log.error("异常：[{}]", e.getMessage());
        return BaseResponse.builder()
                .code(ResultCode.FAILURE)
                .message("异常")
                .build();
    }
}
