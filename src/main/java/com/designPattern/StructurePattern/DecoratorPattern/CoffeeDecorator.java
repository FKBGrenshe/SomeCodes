package com.designPattern.StructurePattern.DecoratorPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-29
 * @Description: Decorator抽象装饰器 -- 为后续做咖啡提供统一扩展接口
 */
public abstract class CoffeeDecorator implements MakeCoffee {

    protected MakeCoffee makeCoffee;

    public CoffeeDecorator(MakeCoffee makeCoffee) {
        this.makeCoffee = makeCoffee;
    }

    @Override
    public String makeCoffee() {
        return "extend coffee";
    }
}
