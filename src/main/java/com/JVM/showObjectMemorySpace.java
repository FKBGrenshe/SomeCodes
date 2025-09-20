package com.JVM;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-20
 * @Description: 查看对象的内存布局
 */
public class showObjectMemorySpace {

    public static void main(String[] args) {
        showObjectMemorySpace user=new showObjectMemorySpace();
        //查看对象的内存布局
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }

}
