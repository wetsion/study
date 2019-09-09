package com.wetsion.study.string;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author weixin
 * @version 1.0
 * @CLassName PasswordEncoderTest
 * @date 2019/8/21 11:22 AM
 */
public class PasswordEncoderTest {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder(11).encode("weixin"));
    }
}
