package com.wetsion.study.web.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wetsion on 2019/7/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse {

    private String message;

    @Builder.Default
    private ResultCode code = ResultCode.SUCCESS;

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS;
    }

}
