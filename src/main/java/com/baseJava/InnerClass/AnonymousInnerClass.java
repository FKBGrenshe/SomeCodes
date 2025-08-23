package com.baseJava.InnerClass;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-02
 * @Description: 匿名内部类
 */
public class AnonymousInnerClass {

    public static void main(String[] args) {
        A a = new A();
        a.usingInnerFunc(
                new abstractInner() {
                    @Override
                    public void func() {
                        System.out.println("匿名内部类");
                    }
                }
        );
    }

}


interface abstractInner{
    void func();
}

class A{
    public void usingInnerFunc(abstractInner abstractInner){
        abstractInner.func();
    }
}