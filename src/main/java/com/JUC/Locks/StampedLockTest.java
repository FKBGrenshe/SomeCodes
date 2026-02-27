package com.JUC.Locks;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-27
 * @Description: 标记锁
 */
public class StampedLockTest {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();


        long stamp = stampedLock.tryOptimisticRead(); // 尝试获取乐观读锁
        // 执行读操作
        if (stampedLock.validate(stamp)){
            // 检查是否可用
            // 执行读操作
        }else {
            // 尝试获取悲观读锁
            stamp = stampedLock.readLock();
            try {
                // 执行读操作
            }finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
    }

}
