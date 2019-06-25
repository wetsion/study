package com.wetsion.study.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wetsion.study.common.entity.Food;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author weixin
 * @version 1.0
 * @CLassName FoodMapper
 * @date 2019/4/4 5:56 PM
 */
@Mapper
@CacheNamespace
public interface FoodMapper extends BaseMapper<Food> {
}
