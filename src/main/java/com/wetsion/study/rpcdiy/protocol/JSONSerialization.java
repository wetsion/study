package com.wetsion.study.rpcdiy.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 用于序列化，使用jackson
 *
 * @author weixin
 * @version 1.0
 * @CLassName JSONSerialization
 * @date 2019/2/27 5:20 PM
 */
public class JSONSerialization implements Serialization {

    private ObjectMapper objectMapper;

    public JSONSerialization() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> byte[] serialize(T obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deSerialize(byte[] data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
