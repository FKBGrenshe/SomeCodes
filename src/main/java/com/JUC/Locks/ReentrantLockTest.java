package com.JUC.Locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-27
 * @Description: 可重入锁
 */
public class ReentrantLockTest {


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(); // 创建锁对象

        reentrantLock.lock();

        try {
            // 执行需要加锁的代码
        } finally {
            reentrantLock.unlock(); // 释放锁
        }

    }
}
