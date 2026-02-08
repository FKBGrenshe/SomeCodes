package com.designPattern.CreationPattern.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-08
 * @Description: 基于同步的懒汉式单例
 */
public class Singleton_LazyAndSynchronized {
    // 私有化构造方法
    private Singleton_LazyAndSynchronized(){}
    // 全局唯一访问变量
    public static Singleton_LazyAndSynchronized INSTANCE = null;
    // 对外静态访问
    public synchronized static Singleton_LazyAndSynchronized getInstance(){
        if (INSTANCE==null){
            INSTANCE = new Singleton_LazyAndSynchronized();
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 反射破坏单例
        // 获取空参构造方法
        Constructor<Singleton_LazyAndSynchronized> declaredConstructor = Singleton_LazyAndSynchronized.class.getDeclaredConstructor(null);
        // 设置强制访问
        declaredConstructor.setAccessible(true);
        // 创建实例
        Singleton_LazyAndSynchronized instance_1 = declaredConstructor.newInstance();
        Singleton_LazyAndSynchronized instance_2 = declaredConstructor.newInstance();

        System.out.println("two Object .equal = " + Objects.equals(instance_1,instance_2));

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("多线程创建的单例：" +
                        Singleton_LazyAndSynchronized.getInstance());
            }).start();
        }
    }
}
