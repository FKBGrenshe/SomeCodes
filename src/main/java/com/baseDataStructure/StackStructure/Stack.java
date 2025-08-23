package com.baseDataStructure.StackStructure;

/**
 * 栈接口
 */
public interface Stack<E> {

    public boolean push(E value);

    public E pop();

    public E peek();

    public boolean isEmpty();

    public boolean isFull();


}
