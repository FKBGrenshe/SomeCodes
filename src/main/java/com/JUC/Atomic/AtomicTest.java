package com.JUC.Atomic;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-03-01
 * @Description: 原子类
 */
public class AtomicTest {

    int res = 0;
    AtomicInteger resAtomic = new AtomicInteger(0);

    public void normalConcurrentAddTest() throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    int tempRes = res;
                    res = tempRes+1;
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            threadPool.submit(task);
        }

        Thread.sleep(10);
        System.out.println("/////////finally--result=///////////"+res);

        threadPool.shutdown();
    }

    public void atomicConcurrentAddtest() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    resAtomic.getAndAdd(1);
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            threadPool.submit(task);
        }
        Thread.sleep(10);
        System.out.println("/////////finally--result=///////////"+resAtomic.get());

        threadPool.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicTest atomicTest = new AtomicTest();
        atomicTest.normalConcurrentAddTest();
        atomicTest.atomicConcurrentAddtest();
    }


}
