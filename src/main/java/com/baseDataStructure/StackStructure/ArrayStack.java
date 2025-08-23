package com.baseDataStructure.StackStructure;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 基于数组的栈
 */
public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    /*
    底    顶
    0 1 2 3
    a
      top
     */

    static class Node<E>{
        E value;
        Node next;

        public Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private E[] array;
    private int top; // 栈顶指针

    @SuppressWarnings("all")
    public ArrayStack(int capcity) {
        this.array = (E[])new Object[capcity];
    }

    @Override
    public boolean push(E value) {
        if (isFull()){
            return false;
        }
        array[top] = value;
        top++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            return null;
        }
        E popVal = array[--top];
        return popVal;
    }

    @Override
    public E peek() {
        return array[top-1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int tempIdx = top;
            @Override
            public boolean hasNext() {
                return tempIdx > 0;
            }

            @Override
            public E next() {
                return array[--tempIdx];
            }
        };
    }
}
