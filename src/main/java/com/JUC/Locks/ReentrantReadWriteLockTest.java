package com.JUC.Locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-27
 * @Description: 可重入读写锁
 */
public class ReentrantReadWriteLockTest {


    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        reentrantReadWriteLock.readLock().lock(); // 获取读锁
        try {
            // 执行需要读取共享资源的代码
        } finally {
            reentrantReadWriteLock.readLock().unlock(); // 释放读锁
        }
    }
}
