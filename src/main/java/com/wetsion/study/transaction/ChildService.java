package com.wetsion.study.transaction;

import com.wetsion.study.common.entity.Food;
import com.wetsion.study.common.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ChildService
 * @date 2019/4/4 6:14 PM
 */
@Service
public class ChildService implements ParentInterface{
//public class ChildService extends ParentService{

    @Autowired
    FoodMapper foodMapper;

    public void childTest() {
        Food food = new Food();
        food.setColor("red");
        food.setName("apple");
        foodMapper.insert(food);
        System.out.println(2/0);

    }
}
