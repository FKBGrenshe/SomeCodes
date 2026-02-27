package com.JUC.BasicJUC;

import org.springframework.data.relational.core.sql.In;

import java.util.concurrent.*;

import static java.lang.Thread.currentThread;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-21
 * @Description: 创建线程的不同方式
 */
public class NewJavaThread {

    // way1：继承thread
    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("WAY1:running:"+currentThread().getName());
        }
    }

    /**
     * 本质：重写run方法 - start让JVM创建OS线程
     * 缺点：
     *  1. java 单线程限制
     *  2. 任务逻辑与线程控制混合
     *  3. 不利于线程复用
     */
    public void newThreadWay1(){
        new MyThread().start();
    }

    // way2：实现Runnable
    class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("WAY2:running:"+currentThread().getName());
        }
    }

    /**
     * 任务和线程解耦，更符合面向接口设计 but！！无返回值
     * 优点：可扩展、可配合线程池使用
     */
    public void newThreadWay2(){
        new Thread(new Task()).start();
    }

    // way3：实现Callable
    /**
     * 必须配合FutureTask/线程池使用，有返回值，可以抛出异常
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Integer newThreadWay3() throws ExecutionException, InterruptedException {

        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("WAY3:running:"+currentThread().getName());
                return 42;
            }
        });

        new Thread(integerFutureTask).start();
        Integer result = integerFutureTask.get();
        return result;
    }

    // way4：线程池
    /**
     * 线程复用-任务排队-避免频繁创建销毁线程
     * 性能高-可控(核心线程数、队列大小)
     */
    public void newThreadWay4(){
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(()-> System.out.printf("WAY4:running:"+currentThread().getName()));
        System.out.println("pool shut down");
        pool.shutdown();
    }

    // way5：异步编排
    public void newThreadWay5(){
        CompletableFuture.supplyAsync(()->42)
                .thenApply(x -> x+1)
                .thenAccept(System.out::println);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NewJavaThread mainThread = new NewJavaThread();

        // way1
        mainThread.newThreadWay1();
        // way2
        mainThread.newThreadWay2();
        // way3
        Integer res = mainThread.newThreadWay3();
        System.out.println("way3:result:" + res);
        // way4
        mainThread.newThreadWay4();
        // way5
        mainThread.newThreadWay5();
    }

}
