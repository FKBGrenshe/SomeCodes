package com.baseDataStructure.QueueStructure.DequeStructure;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-07
 * @Description: 基于循环数组的双端队列
 * -xxx 两个指针，head 和 tail
 *      空：head = tail； 满：tail+1 = head  （因此，tail指针需要占据一个数组位置，array.length-1才是deque的容量）
 *
 */
public class ArrayDeque<E> implements Deque<E>, Iterable<E>{

//    int tail

    @Override
    public boolean offerSFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
