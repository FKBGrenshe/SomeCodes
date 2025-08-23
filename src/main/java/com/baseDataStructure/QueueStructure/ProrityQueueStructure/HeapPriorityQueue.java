package com.baseDataStructure.QueueStructure.ProrityQueueStructure;

import com.baseDataStructure.QueueStructure.Queue;


/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-08
 * @Description: 基于堆的优先级队列
 */
public class HeapPriorityQueue<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public HeapPriorityQueue(int capacity) {
        this.size = 0;
        array = new Priority[capacity];
    }

    /**
     * -xxx 插入：新元素入堆
     *      1. 正常插入应该在数组末尾位置
     *      2. 找到该位置的父节点位置，并进行优先级判断
     *      3. 若不符合父节点优先级>子节点优先级，父节点向下移动
     *          父节点插入到数组末尾位置，新元素应该插入在父节点位置
     *      4. 继续判断，新元素的优先级与父节点的父节点的优先级
     *      5. 一直循环判断，直到相应父节点优先级>新元素优先级 || 新元素位置为0根节点
     * @param value
     * @return
     */
    @Override
    public boolean offer(E value) {
        if (isFull()){
            return false;
        }
        int childIdx = size++;
        int parentIdX = (childIdx-1)/2;

        while (childIdx >0 && array[parentIdX].priority() < value.priority()){
            array[childIdx] = array[parentIdX];
            childIdx = parentIdX;
            parentIdX = (childIdx-1)/2;
        }
        array[childIdx] = value;
        return true;
    }

    /**
     * -xxx 删除：将根节点出堆
     *      1. 将根节点和最后节点进行交换
     *      2. 将最后节点进行“下潜”操作
     *          1） 最后节点不断和自己的两个子节点进行对别
     *              - 如果该节点优先级比自己的两个子节点都大，则已经下潜到了合适的位置
     *              - 否则，和两个子节点中优先级最大的进行交换，继续进行比较
     * @return
     */
    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        Priority maxNode = array[0];
        array[0] = array[size-1];
        array[--size] = null; //helpGC
        int lastIdx = 0;
        int leftChildIdx = 1;
        int rightChildIdx = 2;
        Priority temp;
        while ( leftChildIdx < size && rightChildIdx < size
                && array[lastIdx].priority() < array[leftChildIdx].priority()
                && array[lastIdx].priority() < array[rightChildIdx].priority()){
            temp = array[lastIdx];
            if (array[leftChildIdx].priority() > array[rightChildIdx].priority()){
                array[lastIdx] = array[leftChildIdx];
                array[leftChildIdx] = temp;
                lastIdx = leftChildIdx;
            }else {
                array[lastIdx] = array[rightChildIdx];
                array[rightChildIdx] = temp;
                lastIdx = rightChildIdx;
            }
            leftChildIdx = 2*lastIdx+1;
            rightChildIdx = 2*lastIdx+2;
        }

        if ( leftChildIdx < size && array[lastIdx].priority() < array[leftChildIdx].priority()){
            temp = array[lastIdx];
            array[lastIdx] = array[leftChildIdx];
            array[leftChildIdx] = temp;
        }

        return (E) maxNode;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
