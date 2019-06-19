package com.wetsion.study.multi_thread;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ThreadLocalTest
 * @date 2019/4/12 2:26 PM
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        SetTest setTest = new SetTest();
        GetTest getTest = new GetTest();
        for (int i = 0; i < 15; i++) {
            final String resouce1 = "线程-" + i;
            final String resouce2 = " value = (" + i + ")";
            new Thread() {
                @Override
                public void run() {
                    setTest.set1(resouce1);
                    setTest.set2(resouce2);
                    getTest.get();
                }
            }.start();
        }
    }

    static class ResourceTest {
        public static final ThreadLocal<String> R1 = new ThreadLocal<>();

        public static final ThreadLocal<String> R2 = new ThreadLocal<>();
    }

    static class SetTest {
        void set1(String r1) {
            ResourceTest.R1.set(r1);
        }
        void set2(String r2) {
            ResourceTest.R2.set(r2);
        }
    }

    static class GetTest {
        void get() {
            System.out.println(ResourceTest.R1.get() + ":" + ResourceTest.R2.get());
        }
    }

}
