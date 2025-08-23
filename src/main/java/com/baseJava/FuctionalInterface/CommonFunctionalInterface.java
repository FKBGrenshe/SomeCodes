package com.baseJava.FuctionalInterface;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 常用函数式接口
 */
public class CommonFunctionalInterface {

    // 定义一个方法，返回一个整数数据
    public static Integer usingSupplier(Supplier<Integer> supplier){
        return supplier.get();
    }


}
