package com.JUC.AQS;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-21
 * @Description: AQS框架-可重入锁
 */
public class ReentrantLockAnalysis {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
    }

}

class OrderService{
    // 使用 static 每个线程拿到的是同一把锁
    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    public void createOder(){
        // 同一时间，只允许一个线程创建订单
        reentrantLock.lock();
        // 通常 lock之后紧跟 try语句
        try {
            //
            System.out.println("thread serviceing --" + Thread.currentThread().getName());
        }finally {
            // 释放锁
            reentrantLock.unlock();
        }
    }

}
