package com.JUC.AtomicClass;

import java.util.concurrent.atomic.*;

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

    public void testAtomicStampedReference(){
        AtomicStampedReference<String> asr = new AtomicStampedReference<>("SnailClimb", 1);

        int[] initialStamp = new int[1];
        String initialRef = asr.get(initialStamp);
        System.out.println("Initial Reference: " + initialRef + ", Initial Stamp: " + initialStamp[0]);

        // 更新值 和 版本号
        int oldStamp = initialStamp[0];
        String oldRef = initialRef;
        String newRef = "Daisy";
        int newStamp = oldStamp + 1;

        boolean isUpdated = asr.compareAndSet(oldRef, newRef, oldStamp, newStamp);
        System.out.println("Update Success" + isUpdated);

        // 打印更新后的值和版本号
        int[] updatedStamp = new int[1];
        String updatedRef = asr.get(updatedStamp);
        System.out.println("Updated Reference: " + updatedRef + ", Updated Stamp: " + updatedStamp[0]);

        // 尝试用错误的版本号更新
        boolean isUpdatedWithWrongStamp = asr.compareAndSet(newRef, "John", oldStamp, newStamp + 1);
        System.out.println("Update with Wrong Stamp Success: " + isUpdatedWithWrongStamp);

        // 打印最终的值和版本号
        int[] finalStamp = new int[1];
        String finalRef = asr.get(finalStamp);
        System.out.println("Final Reference: " + finalRef + ", Final Stamp: " + finalStamp[0]);
    }

    public void testAtomicMarkableReference(){
        // 创建一个 AtomicMarkableReference 对象，初始值为 "SnailClimb"，初始标记为 false
        AtomicMarkableReference<String> amr = new AtomicMarkableReference<>("SnailClimb", false);

        // 打印初始值和标记
        boolean[] initialMark = new boolean[1];
        String initialRef = amr.get(initialMark);
        System.out.println("Initial Reference: " + initialRef + ", Initial Mark: " + initialMark[0]);

        // 更新值和标记
        String oldRef = initialRef;
        String newRef = "Daisy";
        boolean oldMark = initialMark[0];
        boolean newMark = true;

        boolean isUpdated = amr.compareAndSet(oldRef, newRef, oldMark, newMark);
        System.out.println("Update Success: " + isUpdated);

        // 打印更新后的值和标记
        boolean[] updatedMark = new boolean[1];
        String updatedRef = amr.get(updatedMark);
        System.out.println("Updated Reference: " + updatedRef + ", Updated Mark: " + updatedMark[0]);

        // 尝试用错误的标记更新
        boolean isUpdatedWithWrongMark = amr.compareAndSet(newRef, "John", oldMark, !newMark);
        System.out.println("Update with Wrong Mark Success: " + isUpdatedWithWrongMark);

        // 打印最终的值和标记
        boolean[] finalMark = new boolean[1];
        String finalRef = amr.get(finalMark);
        System.out.println("Final Reference: " + finalRef + ", Final Mark: " + finalMark[0]);
    }

    public void testAtomicIntegerFieldUpdater(){
        // 创建 AtomicIntegerFieldUpdater 对象
        AtomicIntegerFieldUpdater<Person> ageUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        // 创建 Person 对象 Person
        Person person = new Person("SnailClimb", 22);
        // 打印初始值
        System.out.println("Initial Person: " + person);
        // 更新 age 字段
        ageUpdater.incrementAndGet(person);
        // 自增
        System.out.println("After Increment: " + person);
        ageUpdater.addAndGet(person, 5);
        // 增加 5
        System.out.println("After Adding 5: " + person);
        ageUpdater.compareAndSet(person, 28, 30);
        // 如果当前值是 28，则设置为 30
        System.out.println("After Compare and Set (28 to 30): " + person);
        // 尝试使用错误的比较值进行更新
        boolean isUpdated = ageUpdater.compareAndSet(person, 28, 35);
        // 这次应该失败
        System.out.println("Compare and Set (28 to 35) Success: " + isUpdated);
        System.out.println("Final Person: " + person);
    }


    public static void main(String[] args) {
        AtomicAnalysis obj = new AtomicAnalysis();
//        obj.testAtomicInteger();
        System.out.println("------------------------------------------------------------------");
//        obj.testAtomicIntegerArray();
        System.out.println("------------------------------------------------------------------");
        obj.testAtomicReference();
        System.out.println("------------------------------------------------------------------");
        obj.testAtomicStampedReference();
        System.out.println("------------------------------------------------------------------");
        obj.testAtomicMarkableReference();
        System.out.println("------------------------------------------------------------------");
        obj.testAtomicIntegerFieldUpdater();
    }

}

class Person{
    String name;
    volatile int age;

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
