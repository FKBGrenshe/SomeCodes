package com.JUC.AQS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-21
 * @Description: 条件
 */
public class ConditionAnalysis {



}

class BoundedBuffer{
    private static final Logger log = LoggerFactory.getLogger(BoundedBuffer.class);
    final Lock lock = new ReentrantLock();
    // condition 依赖于 lock 产生
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    // 生产
    public void put(Object x) throws InterruptedException{
        lock.lock();
        try{
            while (count == items.length){
                notFull.await(); // 队列已满，等待，直到 not full才能继续生产
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal(); // 生产成功，队列已经not Empty了，发个通知出去
        }finally {
            lock.unlock();
        }
    }

    // 消费
    public Object take() throws InterruptedException{
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await(); // 队列为空，等待，直到队列 not empty，才能继续消费
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal(); // 已经消费了一个，队列 not full， 发个通知
            return x;
        }finally {
            lock.unlock();
        }
    }
}
