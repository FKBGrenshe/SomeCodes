package com.baseDataStructure.HeapStructure;

/**
 * 堆的抽象接口
 */
public interface Heap {

    /**
     * 建堆
     */
    void insert(int value);

    /**
     * @return 返回堆顶元素
     */
    int peek();

    /**
     * 返回并删除堆顶元素，重新建堆
     */
    int poll();
    /*上浮*/
    void siftUp(int childIdx);
    /*下潜*/
    void siftDown(int parentIdx);
    /*Floyd建堆*/
    void heapify();
    /*大小*/
    int size();
    /*判空*/
    boolean isEmpty();



}
