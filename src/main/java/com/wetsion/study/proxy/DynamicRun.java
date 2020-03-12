package com.wetsion.study.proxy;


import com.wetsion.study.classloader.MyClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 动态运行Java
 *
 * @author weixin
 * @version 1.0
 * @CLassName DynamicRun
 * @date 2020/3/9 5:26 PM
 */
public class DynamicRun {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {


        MyClassLoader classLoader = new MyClassLoader();

        Class clazz = classLoader.defineClass("com.wetsion.study.proxy.DynamicHelloProxy", generateByte());
        System.out.println(clazz.newInstance());
    }

    public static byte[] generateByte() {
        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8,
                Opcodes.ACC_PUBLIC,
                "com/wetsion/study/proxy/DynamicHelloProxy",
                null,
                "java/lang/Object",
                new String[]{"com/wetsion/study/proxy/DynamicHello"});

        MethodVisitor methodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC,
                "sayHello",
                "()Ljava/lang/Object;",
                null,
                null);

        methodVisitor.visitCode();

        classWriter.visitEnd();

        byte[] bytes = classWriter.toByteArray();
        try {
            FileOutputStream fos = new FileOutputStream(new File("src/main/java/com/wetsion/study/proxy/DynamicHelloProxy.class"));
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;

    }
}
