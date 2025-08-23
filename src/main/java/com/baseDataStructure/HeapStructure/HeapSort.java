package com.baseDataStructure.HeapStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-11
 * @Description: 堆排序
 */
public class HeapSort {

    public int[] sortByHeap(int[] arr){
        MaxHeap maxHeap = new MaxHeap(arr);
        int[] array = maxHeap.array;
        int times = maxHeap.size;
        for (int i = 0; i < times; i++) {
            maxHeap.poll();
        }
        return array;
    }

}
