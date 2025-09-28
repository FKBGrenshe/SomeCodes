package com.JUC.FutureTask;

import java.util.concurrent.*;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-28
 * @Description: callable接口&futuretask配合
 */
public class futureTask {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> task = new Callable<>() {
            @Override
            public String call() throws Exception {
                return "this is a callable func";
            }
        };

        // 提交任务到ExecutorService执行，并获取Future对象
        Future[] futures = new Future[10];
        for (int i = 0; i < 10; i++) {
            futures[i] = executorService.submit(task);
        }

        // 通过Future对象获取任务结果
        for (int i = 0; i < 10; i++) {
            System.out.println(futures[i].get());
        }

        executorService.shutdown();

    }


}
