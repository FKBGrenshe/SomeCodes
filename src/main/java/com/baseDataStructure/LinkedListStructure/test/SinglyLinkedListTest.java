package com.baseDataStructure.LinkedListStructure.test;

import com.baseDataStructure.LinkedListStructure.SinglyLinkedListWithIterable;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-01
 * @Description: 单向列表测试类
 */
public class SinglyLinkedListTest {

    public static void main(String[] args) {

        SinglyLinkedListWithIterable list = new SinglyLinkedListWithIterable();
//        list.addFirst(1);
//        list.addFirst(2);
//        list.addFirst(3);
//        list.addFirst(4);

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next);
        }

    }

}
