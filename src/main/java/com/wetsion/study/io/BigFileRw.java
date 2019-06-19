package com.wetsion.study.io;

import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weixin
 * @version 1.0
 * @CLassName BigFileRw
 * @date 2019/6/10 4:37 PM
 */
public class BigFileRw {

    static String path = "/Users/weixin/dev/IdeaProjects/rms/log/application.log";

    @Test
    public void read() throws IOException {

        final int BUFFER_SIZE = 0x300000;
        File file = new File(path);
        MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY,
                file.length() / 2, file.length() / 2);

        byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容

        long start = System.currentTimeMillis();

        for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {

            if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {

                for (int i = 0; i < BUFFER_SIZE; i++) {

                    dst[i] = inputBuffer.get(offset + i);
                }

            } else {

                for (int i = 0; i < inputBuffer.capacity() - offset; i++) {

                    dst[i] = inputBuffer.get(offset + i);

                }
            }

            int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                    : inputBuffer.capacity() % BUFFER_SIZE;

            System.out.println(new String(dst, 0, length));
            System.out.println("biantai");
            // new String(dst,0,length)这样可以取出缓存保存的字符串，可以对其进行操作

        }

        long end = System.currentTimeMillis();

        System.out.println("读取文件文件一半内容花费：" + (end - start) + "毫秒");
    }

    @Test
    public void bufferRead() throws IOException {
        long start = System.currentTimeMillis();
        File file = new File(path);
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(fis,"utf-8"), 10 * 1024 * 1024);// 用5M的缓冲读取文本文件

        String line = "";
        while((line = reader.readLine()) != null){
            //TODO: write your business
            System.out.println(line);
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
