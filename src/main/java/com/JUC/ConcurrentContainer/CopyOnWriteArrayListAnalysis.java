package com.JUC.ConcurrentContainer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-15
 * @Description: 写时复制数组
 */
public class CopyOnWriteArrayListAnalysis {

    public static void main(String[] args) {

        // 创建一个 CopyOnWriteArrayList对象
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();

        // 向列表中添加元素
        list.add("Java");
        list.add("Python");
        list.add("C++");
        System.out.println("初始列表：" + list);

        // 使用get方法获取指定位置的元素
        System.out.println("index = 1 value is: " + list.get(1));

        // try remove()
        // 使用 remove 方法删除指定元素
        boolean result = list.remove("C++");
        System.out.println("删除结果：" + result);
        System.out.println("列表删除元素后为：" + list);

        list.set(1, "Golang");
        System.out.println("update [1] and show : " + list);

        // set
        System.out.println("list size is: "+list.size());


// 使用 removeAll 方法删除指定集合中所有出现的元素
        result = list.removeAll(List.of("Java", "Golang"));
        System.out.println("批量删除结果：" + result);
        System.out.println("列表批量删除元素后为：" + list);

// 使用 clear 方法清空列表中所有元素
        list.clear();
        System.out.println("列表清空后为：" + list);
    }

}
