package com.baseDataStructure.Range;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 插入排序
 */
public class InsertionSort {


    int[] sort(int[] array, int low){
        doSort(array,low);
        return array;
    }

    private void doSort(int[] array, int low) {
        if (low == array.length){
            return;
        }
        int temp = array[low];
        int idx = low-1;
        //自右向左找插入位置，如果比待插入元素大，则不断右移，空出插入位置
        while ( idx>=0 && array[idx] > temp){
            array[idx+1] = array[idx];
            idx--;
        }
        // 要么找到了插入位置
        // 要么idx=-1查遍了所有已排序区域都比他大，得放到最前头
        array[idx+1] = temp;
        doSort(array,low+1);
    }


}
