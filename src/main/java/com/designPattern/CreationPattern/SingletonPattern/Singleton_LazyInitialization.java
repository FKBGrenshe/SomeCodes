package com.designPattern.CreationPattern.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-08
 * @Description: 单例-懒汉式
 */
public class Singleton_LazyInitialization {

    // 私有化构造方法
    private Singleton_LazyInitialization(){}
    // 全局唯一实例
    public static Singleton_LazyInitialization INSTANCE = null;
    // 对外静态访问方法
    public static Singleton_LazyInitialization getInstance(){
        if (Objects.isNull(INSTANCE)){
            INSTANCE = new Singleton_LazyInitialization();
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 反射破坏单例
        // 获取空参构造方法
        Constructor<Singleton_LazyInitialization> declaredConstructor = Singleton_LazyInitialization.class.getDeclaredConstructor(null);
        // 设置强制访问
        declaredConstructor.setAccessible(true);
        // 创建实例
        Singleton_LazyInitialization instance_1 = declaredConstructor.newInstance();
        Singleton_LazyInitialization instance_2 = declaredConstructor.newInstance();

        System.out.println("two Object .equal = " + Objects.equals(instance_1,instance_2));

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("多线程创建的单例：" +
                        Singleton_LazyInitialization.getInstance());
            }).start();
        }
    }

}
