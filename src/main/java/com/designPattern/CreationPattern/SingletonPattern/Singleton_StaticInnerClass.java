package com.designPattern.CreationPattern.SingletonPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-08
 * @Description: 静态内部类创建单例
 */
public class Singleton_StaticInnerClass {

    private Singleton_StaticInnerClass(){}

    private static class Singleton_StaticInnerClass_Holder{
        private static final Singleton_StaticInnerClass INSTANCE = new Singleton_StaticInnerClass();
    }

    public static Singleton_StaticInnerClass getInstance(){
        return Singleton_StaticInnerClass_Holder.INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 反射破坏
        Constructor<Singleton_StaticInnerClass> declaredConstructor = Singleton_StaticInnerClass.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Singleton_StaticInnerClass instance_1 = declaredConstructor.newInstance();
        Singleton_StaticInnerClass instance_2 = declaredConstructor.newInstance();
        System.out.printf("equal="+ Objects.equals(instance_1,instance_2));


        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println("多线程创建的单例：" +
                        Singleton_StaticInnerClass.getInstance());
            }).start();
        }


    }

}
