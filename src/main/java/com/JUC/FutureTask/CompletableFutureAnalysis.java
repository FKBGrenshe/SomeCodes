package com.JUC.FutureTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-17
 * @Description: CompletableFuture详解
 */
public class CompletableFutureAnalysis {

    /*
          -> func2
    func1           -> func4
          -> func3
     */
    public static void func1() throws RuntimeException {
        System.out.printf("func1\n");
//        Thread.sleep(1000);
    }
    public static void func2() throws RuntimeException {
        System.out.printf("func2\n");
//        Thread.sleep(1000);
    }
    public static void func3() throws RuntimeException {
        System.out.printf("func3\n");
//        Thread.sleep(1000);
    }
    public static void func4() throws RuntimeException {
        System.out.printf("func4\n");
//        Thread.sleep(1000);
    }
    //////////////////////////////////////////////

    public static void testCompletableFuture() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello\n");
        System.out.println(future.get());

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> func1())
                .thenCompose(v ->CompletableFuture.allOf(
                        CompletableFuture.runAsync(()->func2()),
                        CompletableFuture.runAsync(()->func3()
                        ))
                )
                .thenRun(() -> func4())
                .exceptionally((ex)->{
                    System.out.println(ex.toString());
                    throw new RuntimeException(ex.toString());
                });

        voidCompletableFuture.join();
        System.out.println("all had been done\n");

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testCompletableFuture();
    }
}
