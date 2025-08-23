package com.baseDataStructure.LinkedListStructure;

import java.util.function.Consumer;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-01
 * @Description: 单向链表
 */
public class SinglyLinkedList {
    Node head;

    /**
     * 节点类
     * -xxx: 节点&链表属于组合关系，将Node节点类作为内部类，可以对外隐藏Node节点类的实现，
     *      使coder更专注于LinkedList使用而非Node类使用 -- 对外暴露的越少越好（外界无法访问）
     */
    private static class Node{
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value){
        /*
        --xxx Ver1：初始版
        if (head.next == null){
        //1. 链表为空
            head = new Node(value, null);
        }else {
        //2. 链表非空
            head = new Node(value, head);
        }
        */
        /*Ver2：一行代码即可*/
        head = new Node(value, head);
    }


    public void loop(Consumer<Integer> consumer){
        Node tmp = head;
        while (tmp != null){
            consumer.accept(tmp.value);
            tmp = tmp.next;
        }
    }





}

