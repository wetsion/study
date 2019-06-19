package com.wetsion.study.java8feature;

import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author weixin
 * @version 1.0
 * @CLassName FunctionDemo
 * @date 2019/6/4 2:55 PM
 */
public class FunctionDemo {

    @Test
    public void t1() {
        Map<String, Function<MathParam, Integer>> actions = new HashMap<>();
        actions.put("add", a -> a.getA() + a.getB());
        actions.put("sub", a -> a.getA() - a.getB());
        actions.put("multi", a -> a.getA() * a.getB());
        actions.put("div", a -> a.getA() / a.getB());
        int r1 = actions.get("add").apply(new MathParam(2, 1));
        int r2 = actions.get("sub").apply(new MathParam(2, 1));
        System.out.println(r1);
        System.out.println(r2);
    }

    @Data
    static class MathParam {
        Integer a;

        Integer b;

        MathParam(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

    }
}
