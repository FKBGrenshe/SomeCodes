package com.designPattern.BehaviorPattern.TemplateMethod;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-25
 * @Description: 具体类B：实现了具体算法步骤
 */
public class ConcreteClassB extends AbstractClass{
    @Override
    boolean step2() {
        return true;
    }

    @Override
    void step3() {

    }

    @Override
    void step4() {
        System.out.println("B类只会走步骤2和4");
    }
}
