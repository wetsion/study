package com.wetsion.study.transaction;

import com.wetsion.study.common.entity.Food;
import com.wetsion.study.common.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ParentService
 * @date 2019/4/4 5:21 PM
 */
@Transactional
//@Service
public class ParentService {

    @Autowired
    FoodMapper foodMapper;

    public void test() {
        Food food = new Food();
        food.setColor("red");
        food.setName("apple");
        foodMapper.insert(food);
        System.out.println(2/0);

    }
}
