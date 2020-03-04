package com.wetsion.study.javacore;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author weixin
 * @version 1.0
 * @CLassName StaticTest
 * @date 2019/10/30 3:50 PM
 */
public class StaticTest {
    public static void main(String[] args) {
        List<InnerObj> list = new LinkedList<>();
        while (true) {
            InnerObj innerObj = new InnerObj();
            innerObj.setName("aaaaaaaaaaa");
            list.add(innerObj);
        }
    }

    @Data
    static class InnerObj {
        String name;
    }
}
