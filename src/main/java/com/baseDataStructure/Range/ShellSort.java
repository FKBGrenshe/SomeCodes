package com.baseDataStructure.Range;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 希尔排序
 */
public class ShellSort {

    int[] sort(int[] array){
        int gap = array.length/2;
        int groups = 2;
        while (gap >= 1){
            // 总共多少组
            for (int i = 0; i < groups; i++) {
                // 每个组使用插入排序

                int low = gap+i;
                int right = array.length;

                while (low < right) {
                    int lowVal = array[low];
                    int idx = low-gap;
                    while (idx >= 0 && array[idx] > lowVal) {
                        array[idx+gap] = array[idx];
                        idx -= gap;
                    }

                    array[idx+gap] = lowVal;
                    low += gap;
                }
            }

            gap /= 2;
            groups *= 2;
        }
        return array;
    }

}
