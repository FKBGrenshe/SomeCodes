package com.baseDataStructure.LinkedListStructure;

import javax.swing.*;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-01
 * @Description: 带迭代器的单向链表
 */
public class SinglyLinkedListWithIterable implements Iterable<Integer> {

    Node head;

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
            Node p = head;
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

    public void addFirst(int value){
        head = new Node(value,head);
    }

    private Node findLast(){
        Node lastone;
        for (lastone = head; lastone.next != null || lastone == null; lastone = lastone.next) {}
        return lastone;
    }

    public void addLast(int value) {
        Node last = findLast();

        if (last == null){
            addFirst(value);
            return;
        }
        last.next = new Node(value,null);
    }




}
