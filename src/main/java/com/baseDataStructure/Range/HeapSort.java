package com.baseDataStructure.Range;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 堆排序
 */
public class HeapSort {

    int[] sort(int[] unsortedArray){
        // 建堆
        heapify(unsortedArray,unsortedArray.length);
        // 不断移除堆顶元素 + 下潜
        int curSize = unsortedArray.length - 1;
        while (curSize > 0){
            SortedUtil.exchange(unsortedArray, 0, curSize);
            siftDown(unsortedArray,0,curSize);
            curSize--;
        }
        return unsortedArray;
    }

    void heapify(int[] array, int size){
        for (int i = size/2-1; i >= 0; i--) {
            siftDown(array, i, size);
        }
    }

    // 下潜
    void siftDown(int[] array, int parentIdx, int size){

        while (true){

            int leftChild = 2*parentIdx + 1;
            int rightChild = leftChild+1;
            int maxValIdx =  parentIdx;

            if ( leftChild < size && array[maxValIdx] < array[leftChild]){
                maxValIdx = leftChild;
            }

            if ( rightChild < size && array[maxValIdx] < array[rightChild]){
                maxValIdx = rightChild;
            }

            if (maxValIdx == parentIdx){
                // 不需要下潜
                break;
            }else {
                // 需要下潜
                SortedUtil.exchange(array, maxValIdx, parentIdx);
                parentIdx = maxValIdx;
            }
        }


    }


}
