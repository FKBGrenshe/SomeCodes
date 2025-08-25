package com.designPattern.BehaviorPattern.StrategyPattern;

public class Context {

    // 上下文维护对具体策略的引用
    private Strategy strategy;
    // 设置具体的策略
    public Context(Strategy strategy)
    {
        this.strategy = strategy;
    }
    // 执行策略
    public void executeStrategy()
    {
        strategy.algorithmInterface();
    }

}
