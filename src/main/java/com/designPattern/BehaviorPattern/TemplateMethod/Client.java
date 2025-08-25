package com.designPattern.BehaviorPattern.TemplateMethod;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-25
 * @Description: 客户端
 */
public class Client {
    public static void main(String[] args) {
        AbstractClass templateByA = new ConcreteClassA();
        templateByA.templateMethod();

        AbstractClass templateByB = new ConcreteClassB();
        templateByB.templateMethod();
    }
}
