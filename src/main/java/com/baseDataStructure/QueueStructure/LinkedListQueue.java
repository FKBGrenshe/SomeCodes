package com.baseDataStructure.QueueStructure;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-02
 * @Description: 基于单向环形哨兵链表的队列
 */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E>{

    private static class Node<E>{
        Node<E> next;
        E value;

        public Node(Node<E> next, E value) {
            this.next = next;
            this.value = value;
        }
    }

    Node<E> head = new Node<>(null,null);
    Node<E> tail = head;
    private int size; // 当前的节点数
    private int capacity; // 整个队列的容量

    public LinkedListQueue() {
        tail.next = head;
        capacity = 10;
    }

    public LinkedListQueue(int size) {
        this.size = size;
    }

    @Override
    public boolean offer(E value) {
        if (size == capacity){
            return false;
        }
        Node<E> newNode = new Node<>(head, value);
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }else {
            Node<E> first = head.next;
            head.next = first.next;
            size--;

            if (first == tail){
                // 被删除的是尾指针 -- tail需要移动
                tail = head;
            }

            return first.value;
        }
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }else {
            return head.next.value;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size==capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E val = p.value;
                p = p.next;
                return val;
            }
        };
    }
}
