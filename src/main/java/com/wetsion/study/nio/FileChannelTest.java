package com.wetsion.study.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weixin
 * @version 1.0
 * @CLassName FileChannelTest
 * @date 2019/3/26 6:01 PM
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile =
                new RandomAccessFile("/Users/weixin/Downloads/68.sh", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int byteRead = fileChannel.read(buffer);
        while (byteRead != -1) {
            System.out.println("Read " + byteRead);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println((char)buffer.get());
            }
            buffer.clear();
            byteRead = fileChannel.read(buffer);
        }
        randomAccessFile.close();
    }
}
