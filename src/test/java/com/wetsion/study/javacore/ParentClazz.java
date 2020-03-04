package com.wetsion.study.javacore;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ParentClazz
 * @date 2019/11/18 10:37 AM
 */
public class ParentClazz extends SuperParentClazz {

    private int a = 1;

    private static ChildClazz childClazz = new ChildClazz();

    static {
        System.out.println("static block");
    }

    public ParentClazz() {
        System.out.println("parent con");
    }

    static class ChildClazz {
        ChildClazz() {
            System.out.println("child");
        }
    }

    public static void main(String[] args) {
        ParentClazz parentClazz = new ParentClazz();
    }
}
