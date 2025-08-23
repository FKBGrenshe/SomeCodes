package com.baseDataStructure.HeapStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-12
 * @Description: 小顶堆
 * todo: 将大顶堆换成小顶堆
 */
public class MinHeap implements Heap {
    int[] array;
    int size;

    public MinHeap(int capacity) {
        array = new int[capacity];
    }

    @Override
    public void insert(int value) {
        if (size == array.length){
            System.out.println("heap is full, cannot insert new value");
        }
        array[size++] = value;
        siftUp(size-1);
    }

    @Override
    public int peek() {
        return array[0];
    }

    @Override
    public int poll() {
        if (isEmpty()){
            System.out.println("heap is Empty, cannot poll");
        }
        // todo:
        return -1;
    }

    @Override
    public void siftUp(int childIdx) {
        /*下潜：
        *   1. 找到父节点
        *   2. 与父节点比较，如果大于父节点，则交换
        *   3. 重复，直到比父节点小or无父节点*/
        int tempVal = array[childIdx];
        int parentIdx = (childIdx-1)>>1;
        while (parentIdx >= 0 && array[parentIdx] > array[childIdx]){
            swap(childIdx, parentIdx);
            childIdx = parentIdx;
            parentIdx = (childIdx-1)>>1;
        }
        array[childIdx] = tempVal;
    }

    @Override
    public void siftDown(int parentIdx) {
        int leftChildIdx = (parentIdx << 1) + 1;
        int rightChildIdx = leftChildIdx + 1;
        int max = parentIdx;

        if (leftChildIdx < size){
            max = array[leftChildIdx] < array[max] ? leftChildIdx : max;
        }
        if (rightChildIdx < size){
            max = array[rightChildIdx] < array[max] ? rightChildIdx : max;
        }
        if (max != parentIdx){
            swap(max,parentIdx);
            siftDown(max);
        }
    }

    @Override
    public void heapify() {
        /*Floyd建堆：
        *   1. 找到最后一个非叶子节点
        *   2. 从后向前依次下潜
        */
        int parent = (size >> 1) - 1;

        for (int i = (size>>1)-1; i >= 0; i--) {
            siftDown(parent);
            parent--;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int replace(int newValue){
        /*使用新值对堆顶元素进行替换，再次下潜堆顶元素*/
        int oldValue = array[0];
        array[0] = newValue;
        siftDown(0);
        return oldValue;
    }
}
