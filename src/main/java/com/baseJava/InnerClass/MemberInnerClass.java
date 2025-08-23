package com.baseJava.InnerClass;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-02
 * @Description: 成员内部类
 */
public class MemberInnerClass {
    public static void main(String[] args) {
        // 创建内部类对象
        Outer.Inner inner = new Outer().new Inner();
        inner.show();
    }
}

class Outer{
    // 外部类成员变量
    int number = 10;
    // 外部类成员方法
    public void show(){
        System.out.println(number);
    }

    // xxx 成员内部类
    public class Inner{
        // 内部类成员变量
        private int number = 20;
        // 内部类成员方法
        public void show(){
            // 方法的局部变量
            int number = 30;
            // 访问：内部类的局部变量、内部类的成员变量、外部类的成员变量
            System.out.println(number); // 30
            System.out.println(this.number); // 20
            System.out.println(Outer.this.number); // 10
        }
    }
}
