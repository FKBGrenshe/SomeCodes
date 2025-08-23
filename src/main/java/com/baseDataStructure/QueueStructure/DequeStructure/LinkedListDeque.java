package com.baseDataStructure.QueueStructure.DequeStructure;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-07
 * @Description: 基于环形链表的双端队列
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    int size;
    int capacity;
    Node<E> sentinel = new Node<>(null,null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public boolean offerSFirst(E e) {

        if (isFull()){
            return false;
        }
        Node<E> newNode = new Node<>(e, sentinel.next, sentinel);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()){
            return false;
        }
        Node<E> newNode = new Node<>(e, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()){
            return null;
        }
        Node<E> pollNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        return pollNode.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()){
            return null;
        }
        Node<E> pollNode = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return pollNode.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()){
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()){
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E val = p.value;
                p = p.next;
                return val;
            }
        };
    }

    static class Node<E>{

        E value;
        Node<E> next;
        Node<E> prev;

        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }


}
