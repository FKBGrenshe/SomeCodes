package com.designPattern.BehaviorPattern.TemplateMethod;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-25
 * @Description: 模板类：包含模板方法，各个步骤（有默认实现、抽象步骤待具体子类实现）
 */
public abstract class AbstractClass {

    public final void templateMethod(){
        step1();
        if (step2()){
            step4();
        }else {
            step3();
        }

    }

    void step1(){
        // step1是确定的无需改变的步骤，所有不同模板都需要使用，因此在父类中进行实现，无需放到子类中
        System.out.println("this is step1");
    }
    
    abstract boolean step2();
    abstract void step3();
    abstract void step4();
}
