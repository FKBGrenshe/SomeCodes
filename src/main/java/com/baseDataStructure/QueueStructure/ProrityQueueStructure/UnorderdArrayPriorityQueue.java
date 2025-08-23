package com.baseDataStructure.QueueStructure.ProrityQueueStructure;

import com.baseDataStructure.QueueStructure.Queue;
import jakarta.annotation.Priority;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-08
 * @Description: 基于无序数组的优先级队列
 */
public class UnorderdArrayPriorityQueue<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public UnorderdArrayPriorityQueue(int capacity) {
        array = new Priority[capacity];
        this.size = 0;
    }


    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        array[size++] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        int maxIdx = findMaxIndex();
        E target = (E) array[maxIdx];
        remove(maxIdx);
        return target;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        int maxIdx = findMaxIndex();
        return (E) array[maxIdx];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    private int findMaxIndex(){
        int maxIdx = 0;
        int tempIdx = 0;
        int maxVal = Integer.MIN_VALUE;
        while (tempIdx < size){
            if (array[tempIdx].value() > maxVal){
                maxIdx = tempIdx;
            }
            tempIdx++;
        }
        return maxIdx;
    }

    private void remove(int idx){
        if (idx != size-1){
            System.arraycopy(array,idx+1,array,idx,array.length-1-idx);
        }
        array[--size] = null; // help GC
    }
}
