package com.wetsion.study.rpcdiy.protocol;

/**
 * @author weixin
 * @version 1.0
 * @CLassName Serialization
 * @date 2019/2/27 5:00 PM
 */
public interface Serialization {
    <T> byte[] serialize(T obj);

    <T> T deSerialize(byte[] data, Class<T> clazz);
}
