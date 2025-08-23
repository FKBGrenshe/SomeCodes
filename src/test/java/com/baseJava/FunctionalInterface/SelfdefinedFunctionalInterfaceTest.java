package com.baseJava.FunctionalInterface;

import com.baseJava.FuctionalInterface.SelfdefineFunctionalInterface;
import org.junit.jupiter.api.Test;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 自定义函数时接口测试
 */
public class SelfdefinedFunctionalInterfaceTest {

    @Test
    void test(){

        String message = "this is message";
        SelfdefineFunctionalInterface testInterface = System.out::println;
        testInterface.sayMessage(message);

    }

}
