package com.baseDataStructure.QueueStructure;

public interface Queue<E> {

    boolean offer(E value);

    E poll();

    E peek();

    boolean isEmpty();

    /**
     * 检查队列是否已经满了
     * @return 满：返回true，否则返回false
     */
    boolean isFull();

}
