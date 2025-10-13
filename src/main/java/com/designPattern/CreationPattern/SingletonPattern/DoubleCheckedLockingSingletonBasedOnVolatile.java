package com.designPattern.CreationPattern.SingletonPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-28
 * @Description: 基于volatile的双重检查锁定单例模式
 */
public class DoubleCheckedLockingSingletonBasedOnVolatile {

    private static volatile DoubleCheckedLockingSingletonBasedOnVolatile singleton = null;

    // 一些成员属性
    private int feature1 = 10;
    private int feature2 = 11;
    // 一些成员方法
    public void behavoir1(){System.out.println("this is behavor1");}
    public void behavoir2(){System.out.println("this is behavor2");}

    // 避免通过new初始化对象，构造方法为private
    private DoubleCheckedLockingSingletonBasedOnVolatile(){}

    // 外部得到该单例对象
    public DoubleCheckedLockingSingletonBasedOnVolatile getSingleton(){
        if (singleton == null){
            synchronized (DoubleCheckedLockingSingletonBasedOnVolatile.class){
                if (singleton == null){
                    singleton = new DoubleCheckedLockingSingletonBasedOnVolatile();
                }
            }
        }
        return singleton;
    }
}
