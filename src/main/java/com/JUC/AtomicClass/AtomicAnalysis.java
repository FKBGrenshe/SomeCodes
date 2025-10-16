package com.JUC.AtomicClass;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-16
 * @Description: 原子类分析
 */
public class AtomicAnalysis {

    public void testAtomicInteger(){

        AtomicInteger atomicInt = new AtomicInteger(0);

        int tempValue = atomicInt.getAndSet(3);
        System.out.println("tempValue="+tempValue+" atomicInt="+atomicInt.get());

        tempValue = atomicInt.getAndIncrement();
        System.out.println("tempValue="+tempValue+" atomicInt="+atomicInt.get());

        tempValue = atomicInt.getAndAdd(5);
        System.out.println("tempValue="+tempValue+" atomicInt="+atomicInt.get());

        boolean updateSuccess = atomicInt.compareAndSet(9, 10);
        System.out.println("updateSuccess="+updateSuccess+" atomicInt="+atomicInt.get());

        atomicInt.lazySet(15);
        System.out.println("after layset = "+atomicInt);
    }

    public void testAtomicIntegerArray(){
        int[] nums = {1,2,3,4,5,6};
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(nums);

        System.out.println("initial values in atomicIntegerArray");
        for (int i = 0; i < nums.length; i++) {
            System.out.print("Index " + i + ": " + atomicArray.get(i) + " ");
        }

        int tempValue = atomicArray.getAndSet(0, 2);
        System.out.println("\nAfter getAndSet(0,2)");
        System.out.println("returned value: "+ tempValue);
        for (int j = 0; j < atomicArray.length(); j++) {
            System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
        }

        tempValue = atomicArray.getAndIncrement(0);
        System.out.println("\nAfter getAndIncrement(0):");
        System.out.println("Returned value: " + tempValue);
        for (int j = 0; j < atomicArray.length(); j++) {
            System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
        }

        // 使用 getAndAdd 方法将索引 0 处的值增加 5，并返回旧值
        tempValue = atomicArray.getAndAdd(0, 5);
        System.out.println("\nAfter getAndAdd(0, 5):");
        System.out.println("Returned value: " + tempValue);
        for (int j = 0; j < atomicArray.length(); j++) {
            System.out.print("Index " + j + ": " + atomicArray.get(j) + " ");
        }
    }

    public void testAtomicReference(){
        AtomicReference<Person> ar = new AtomicReference<>(new Person("xiaomei", 22));
        // 打印初始值
        System.out.println("initial person: " + ar.get().toString());

        // 更新值
        Person updatePerson = new Person("Daisy", 20);
        ar.compareAndSet(ar.get(), updatePerson);

        // 打印更新值
        System.out.println("update person: " + ar.get().toString());

        // 尝试再次更新
        Person anotherUpdatePerson = new Person("John", 30);
        boolean isUpdated = ar.compareAndSet(updatePerson, anotherUpdatePerson);

        // 打印是否更新成功及最终值
        System.out.println("Second Update Success: " + isUpdated);
        System.out.println("Final Person: " + ar.get().toString());
    }


    public static void main(String[] args) {
        AtomicAnalysis obj = new AtomicAnalysis();
        obj.testAtomicInteger();
        System.out.println("------------------------------------------------------------------");
        obj.testAtomicIntegerArray();
        System.out.println("------------------------------------------------------------------");
        obj.testAtomicReference();

    }

}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
