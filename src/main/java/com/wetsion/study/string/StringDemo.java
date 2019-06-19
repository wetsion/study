package com.wetsion.study.string;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.MessageFormat;

/**
 * @author weixin
 * @version 1.0
 * @CLassName StringDemo
 * @date 2019/4/4 4:19 PM
 */
public class StringDemo {

    public static void main(String[] args) {
        new StringDemo();
        String email = "weixin@dxy.cn";
        System.out.println(email.substring(0,email.indexOf("@")));

        double a = 2;
        int b = Integer.numberOfLeadingZeros(100013);
        System.out.println(b);

        String xx = "123456789";
        System.out.println(xx.substring(1, 3));

        System.out.println("ab".indexOf("abc"));

        System.out.println(new BCryptPasswordEncoder(11).encode("weixin"));
        System.out.println(new BCryptPasswordEncoder(11).matches("weixin", "$2a$11$qvQ3McwXA/jMgaR6oNxr2OxBaZuhAVUcFtgLpZpogP.WRn.Gn.P.K"));
    }

    public StringDemo() {
        System.out.println(1);
    }

    @Test
    public void t() {
        System.out.println(MessageFormat.format("{name} love", "I"));
    }

    @Test
    public void join() {
        String[] a = {"a", "b"};
        System.out.println(String.join(",", a));
    }
}
