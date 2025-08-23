package com.baseJava.InnerClass;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-02
 * @Description: 静态内部类
 */
public class staticInnerClass {
    public static void main(String[] args) {
        OuterStatic.InnerStatic innerStatic = new OuterStatic.InnerStatic();
        innerStatic.show();
    }
}

class OuterStatic{

    int a = 10;
    static int b = 20;

    public static class InnerStatic{

        int num = 10;
        public void show(){
            System.out.println();
            // System.out.println(a); 静态内部类无法直接访问外部类的实例成员变量
            System.out.println(b); // 可以直接访问外部类的静态成员
        }
    }
}
