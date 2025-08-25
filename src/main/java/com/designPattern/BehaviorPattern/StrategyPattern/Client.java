package com.designPattern.BehaviorPattern.StrategyPattern;

public class Client {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategyA());
        context.executeStrategy();
        context = new Context(new ConcreteStrategyB());
        context.executeStrategy();
    }
}
