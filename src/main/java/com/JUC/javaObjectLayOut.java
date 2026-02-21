package com.JUC;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-17
 * @Description: 对象内存布局
 */
public class javaObjectLayOut {
    public static void main(String[] args) {
        // 使用JOL查看对象内存布局
        Object object = new Object();

        // 无锁状态
        System.out.println("无锁状态：");
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        // 偏向锁状态
        synchronized (object){
            System.out.println("偏向锁状态：");
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }


    }
}
