package com.baseJava.FunctionalInterface;

import com.baseJava.FuctionalInterface.CommonFunctionalInterface;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 常用函数式接口测试
 */
public class CommonFunctionalInterfaceTest {

    @Test
    void supplierTest(){
        Integer integer = CommonFunctionalInterface.usingSupplier(() -> 89);
        System.out.println(integer);
    }

    /*@Test
    void consumerTest(){
        new Consumer<>()
    }*/


}
