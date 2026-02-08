package com.designPattern.CreationPattern.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum Singleton_Enum {
    INSTANCE;

    public void doSomething(){
        System.out.println("do some thing...");
    }

    public static Singleton_Enum getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Singleton_Enum instance = Singleton_Enum.INSTANCE;
        instance.doSomething();

        // 多线程尝试
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println("多线程创建的单例：" +
                        Singleton_Enum.getInstance());
            }).start();
        }

        // 反射破坏尝试 -- 报错，枚举类不可被反射创建实例
//        Constructor<Singleton_Enum> declaredConstructor = Singleton_Enum.class.getDeclaredConstructor(String.class, int.class);
//        declaredConstructor.setAccessible(true);
//        Singleton_Enum newInstance = declaredConstructor.newInstance();
//        System.out.println(newInstance);

    }
}
