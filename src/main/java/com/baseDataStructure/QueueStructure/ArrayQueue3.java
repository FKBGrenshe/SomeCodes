package com.baseDataStructure.QueueStructure;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 基于环形链表的队列
 */
public class ArrayQueue3<E> implements Queue<E>, Iterable<E>{

    private final E[] array;
    private int head = 0;
    private int tail = 0;
    private int nums = 0;

    public ArrayQueue3(int capacity) {
        array = (E[])new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false; // 添加失败
        }
        array[tail] = value;
        tail = (tail+1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        E headObj =  array[head];
        head = (head+1)% array.length;
        return headObj;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return array[head % array.length];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p % array.length];
                p++;
                return value;
            }
        };
    }
}
