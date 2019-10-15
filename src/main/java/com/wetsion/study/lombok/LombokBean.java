package com.wetsion.study.lombok;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author weixin
 * @version 1.0
 * @CLassName LombokBean
 * @date 2019/10/14 4:10 PM
 */
@Data
@Builder(toBuilder = true)
public class LombokBean implements Serializable {

    private String name;

    private String note;

    private Long time;
}
