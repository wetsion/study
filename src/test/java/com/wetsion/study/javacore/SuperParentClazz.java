package com.wetsion.study.javacore;

/**
 * @author weixin
 * @version 1.0
 * @CLassName SuperParentClazz
 * @date 2019/11/18 3:24 PM
 */
public class SuperParentClazz {

    private static ParentClazz.ChildClazz childClazz = new ParentClazz.ChildClazz();

    static {
        System.out.println("super static block");
    }

    public SuperParentClazz() {
        System.out.println("super parent con");
    }
}
