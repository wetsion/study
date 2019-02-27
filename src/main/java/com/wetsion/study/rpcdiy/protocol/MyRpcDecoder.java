package com.wetsion.study.rpcdiy.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义反序列化
 *
 * @author weixin
 * @version 1.0
 * @CLassName MyRpcDecoder
 * @date 2019/2/27 5:27 PM
 */
public class MyRpcDecoder extends ByteToMessageDecoder {

    private Class<?> clazz;

    private Serialization serialization;

    public MyRpcDecoder(Class<?> clazz, Serialization serialization) {
        this.clazz = clazz;
        this.serialization = serialization;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);

        Object obj = serialization.deSerialize(data, clazz);
        list.add(obj);
    }
}
