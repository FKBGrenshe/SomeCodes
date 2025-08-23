package com.baseDataStructure.QueueStructure.DequeStructure;

/**
 * 双端队列基础结构
 */
public interface Deque<E> {

    boolean offerSFirst(E e);

    boolean offerLast(E e);

    E pollFirst();

    E pollLast();

    E peekFirst();

    E peekLast();

    boolean isEmpty();

    boolean isFull();

}
