package com.wetsion.study.mybatis;

import com.wetsion.study.common.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wetsion on 2019/6/25.
 */
@RestController
public class MybatisTestEndpoint {

    @Autowired
    FoodMapper foodMapper;

    @GetMapping("/mybatis/cache/test1")
    public void test1() {
        foodMapper.selectById(1);
        foodMapper.selectById(1);
    }
}
