package com.baseJava.FuctionalInterface;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 自定义函数式接口
 */
@FunctionalInterface
public interface SelfdefineFunctionalInterface <T> {
    void sayMessage(T message);
}
