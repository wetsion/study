package com.wetsion.study.rpcdiy.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author weixin
 * @version 1.0
 * @CLassName MyRpcEncoder
 * @date 2019/2/27 5:46 PM
 */
public class MyRpcEncoder extends MessageToByteEncoder {

    private Class<?> clazz;

    private Serialization serialization;

    public  MyRpcEncoder(Class<?> clazz, Serialization serialization) {
        this.clazz = clazz;
        this.serialization = serialization;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (clazz != null) {
            byte[] bytes = serialization.serialize(msg);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }
    }
}
