package com.wetsion.study.string;

import java.util.Arrays;
import java.util.Date;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ConcatDemo
 * @date 2019/4/25 2:31 PM
 */
public class ConcatDemo {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(concatUrl("a", "b", "/jack", "no.md"));
        System.out.println(System.currentTimeMillis());

        int delta = 7011;
        System.out.println(new Date(System.currentTimeMillis() + delta * 1000L));
    }

    private static String concatUrl(String... args) {
        StringBuffer sb = new StringBuffer();
        Arrays.stream(args).forEach(str -> {
            if (!str.startsWith("/") && str.length() > 0) {
                sb.append("/");
            }
            sb.append(str);
        });
        return sb.toString();
    }
}
