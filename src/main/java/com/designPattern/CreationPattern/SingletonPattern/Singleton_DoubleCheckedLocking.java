package com.designPattern.CreationPattern.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-08
 * @Description: DLC双重检查锁的单例
 */
public class Singleton_DoubleCheckedLocking {

    // 私有化构造方法
    private Singleton_DoubleCheckedLocking(){}
    // 使用 volatile 保证指令的可见性和禁止指令重排
    private static volatile Singleton_DoubleCheckedLocking INSTANCE = null;
    // 对外访问
    public static Singleton_DoubleCheckedLocking getInstance(){
        // 1st检查：避免不必要的同步
        if (INSTANCE == null){
            // 2nd检查：在同步块内部确保只有一个线程创建实例
            synchronized (Singleton_DoubleCheckedLocking.class){
                if (INSTANCE==null){
                    INSTANCE = new Singleton_DoubleCheckedLocking();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 反射破坏单例
        // 获取空参构造方法
        Constructor<Singleton_DoubleCheckedLocking> declaredConstructor = Singleton_DoubleCheckedLocking.class.getDeclaredConstructor(null);
        // 设置强制访问
        declaredConstructor.setAccessible(true);
        // 创建实例
        Singleton_DoubleCheckedLocking instance_1 = declaredConstructor.newInstance();
        Singleton_DoubleCheckedLocking instance_2 = declaredConstructor.newInstance();

        System.out.println("two Object .equal = " + Objects.equals(instance_1,instance_2));

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("多线程创建的单例：" +
                        Singleton_DoubleCheckedLocking.getInstance());
            }).start();
        }
    }

}
