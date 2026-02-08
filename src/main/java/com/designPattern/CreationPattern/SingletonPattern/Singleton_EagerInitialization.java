package com.designPattern.CreationPattern.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-08
 * @Description: 单例-饿汉式
 */
public class Singleton_EagerInitialization {

    // 私有化构造
    private Singleton_EagerInitialization(){}

    // 类加载时就创建实例
    private static final Singleton_EagerInitialization INSTANCE = new Singleton_EagerInitialization();

    // 提供公共访问方法
    public static Singleton_EagerInitialization getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 反射破坏单例
        // 获取空参构造方法
        Constructor<Singleton_EagerInitialization> declaredConstructor = Singleton_EagerInitialization.class.getDeclaredConstructor(null);
        // 设置强制访问
        declaredConstructor.setAccessible(true);
        // 创建实例
        Singleton_EagerInitialization instance_1 = declaredConstructor.newInstance();
        Singleton_EagerInitialization instance_2 = declaredConstructor.newInstance();

        System.out.println("two Object .equal = " + Objects.equals(instance_1,instance_2));
    }

}
