package com.designPattern.StructurePattern.DecoratorPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-29
 * @Description: CooncreteDecorator 具体装饰器 -- 基于抽象装饰器，做额外扩展
 */
public class SepecificCoffee extends CoffeeDecorator {


    public SepecificCoffee(MakeCoffee makeCoffee) {
        super(makeCoffee);
    }

    @Override
    public String makeCoffee() {
        System.out.println("定制化");
        // 定制化流程前序
        String res = super.makeCoffee();
        // 定制化流程后续
        return res;
    }

}
