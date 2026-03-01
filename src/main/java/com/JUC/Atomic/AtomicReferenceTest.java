package com.JUC.Atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-03-01
 * @Description: 原子对象类测试
 */
public class AtomicReferenceTest {

    public void atomicObject(){
        // 创建atomicreference对象并设置初始值
        AtomicReference<Person> atomicReference = new AtomicReference<>(new Person("cc", 22));

        // print initialValue
        System.out.println("Initial Person: "+atomicReference.get().toString());

        // update value
        Person updatePerson = new Person("lazy", 23);
        atomicReference.compareAndSet(atomicReference.get(), updatePerson);

        System.out.println("Updated Person: "+atomicReference.get().toString());

        // update fields
        AtomicReferenceFieldUpdater<Person, String> nameUpdater = AtomicReferenceFieldUpdater.newUpdater(Person.class, String.class, "name");
        nameUpdater.compareAndSet(updatePerson, atomicReference.get().getName(), "newName");

        System.out.println("Updated Person: "+atomicReference.get().toString());

    }

    public static void main(String[] args) {
        AtomicReferenceTest atomicReferenceTest = new AtomicReferenceTest();
        atomicReferenceTest.atomicObject();
    }

}

class Person{
    public volatile String name;
    public volatile int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
