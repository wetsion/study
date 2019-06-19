package com.wetsion.study.string;

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
