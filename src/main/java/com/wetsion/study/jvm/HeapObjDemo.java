package com.wetsion.study.jvm;

/**
 * @author weixin
 * @version 1.0
 * @CLassName HeapObjDemo
 * @date 2019/12/5 3:58 PM
 */
public class HeapObjDemo {

    public static void main(String[] args) throws InterruptedException {
        HeapObjDemo heapObjDemo = new HeapObjDemo();
        for (int i = 0; i < 5000000; i++) {
            initMyObj(i);
        }
        Thread.sleep(600000);
    }

    static void initMyObj(int i) {
        MyObj myObj = new HeapObjDemo().new MyObj(i);
    }

    class MyObj {
        int num;

        MyObj (int num) {
            this.num = num;
        }
    }
}
