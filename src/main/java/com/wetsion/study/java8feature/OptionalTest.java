package com.wetsion.study.java8feature;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author weixin
 * @version 1.0
 * @CLassName OptionalTest
 * @date 2019/5/28 5:40 PM
 */
public class OptionalTest {

    @Test
    public void t1() {
        String a = null;
        String b = "b";
        Optional<String> stringOptional = Optional.empty();
        Optional<String> as = Optional.ofNullable(b).map(s -> s);
        System.out.println(as.get());
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;
        System.out.println(Integer.SIZE - 3);
        System.out.println(-1 << COUNT_BITS);
        System.out.println(-1 << COUNT_BITS | 0);
        System.out.println(0 << COUNT_BITS);
        System.out.println(1 << COUNT_BITS);
        System.out.println(2 << COUNT_BITS);
        System.out.println(3 << COUNT_BITS);
        System.out.println(536870912 & ~CAPACITY);
    }

    @Test
    public void json() {
        Object[] objects = {"aaa", new Jt("wx")};
        System.out.println(JSON.toJSONString(objects));
        Object[] r = JSON.parseObject(JSON.toJSONString(objects), Object[].class);
        Arrays.stream(r).forEach(o -> {
            if (o instanceof JSONObject) {
                ((JSONObject) o).put("name", "wxx");
            }
        });
        System.out.println(JSON.toJSONString(r));
    }

    @Test
    public void objtest() {
        Jt jt = new Jt();
        jt.setName("aaa");
        Jt jt1 = jt;
        jt1.setName("bbb");
        Jt jt2 = jt(jt);
        System.out.println(jt);
        System.out.println(JSON.toJSONString(jt));
        System.out.println(jt1);
        System.out.println(JSON.toJSONString(jt1));
        System.out.println(jt2);
        System.out.println(JSON.toJSONString(jt2));
    }

    private Jt jt(Jt jt) {
        Jt j = jt;
        j.setName("ccc");
        return j;
    }

    static class Jt {
        String name;

        public Jt() {}

        public Jt(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
