package com.baseDataStructure.LinkedListStructure;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-01
 * @Description: 带哨兵的单向链表
 */
public class SinglyLinkedListWithSentinel implements Iterable<Integer> {

    Node head = new Node(666, null);

    public static class Node{
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p;
            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public Integer next() {
                int val = p.value;
                p = p.next;
                return val;
            }
        };
    }


    public void addFirst(int val){

    }

    public void addLast(int val){

    }

    public void loop(Consumer<Integer> consumer){

    }


}
