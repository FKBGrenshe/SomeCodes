package com.baseDataStructure.StackStructure;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 基于链表的栈
 */
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    private int capacity;
    private int size;

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }

    private Node<E> head = new Node<>(null,null);


    @Override
    public boolean push(E value) {
        if (isFull()){
            return false;
        }
        Node<E> newNode = new Node<>(value, head.next);
        head.next = newNode;
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null;
        }
        Node<E> popNode = head.next;
        head.next = popNode.next;
        size--;
        return popNode.value;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        Node<E> popNode = head.next;
        return popNode.value;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    static class Node<E>{
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

}
