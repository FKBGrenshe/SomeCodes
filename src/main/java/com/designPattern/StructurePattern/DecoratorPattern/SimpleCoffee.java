package com.designPattern.StructurePattern.DecoratorPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-29
 * @Description: ConcreteComponent具体实现类 -- 该功能的默认实现
 */
public class SimpleCoffee implements MakeCoffee {


    @Override
    public String makeCoffee() {
        return "Simple Coffee";
    }
}
