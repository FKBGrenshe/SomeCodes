package com.designPattern.BehaviorPattern.TemplateMethod;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-25
 * @Description: 具体类A：实现了具体算法步骤
 */
public class ConcreteClassA extends AbstractClass{
    @Override
    boolean step2() {
        return false;
    }

    @Override
    void step3() {
        System.out.println("A类只会走step3");
    }

    @Override
    void step4() {

    }
}
