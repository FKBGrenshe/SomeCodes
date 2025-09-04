package com.baseDataStructure.Range;

import static com.baseDataStructure.Range.SortedUtil.exchange;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 选择排序
 */
public class SelectionSort {

    int[] sort(int[] unsortedArray){
        int times = unsortedArray.length-1;

        int endIdx = unsortedArray.length-1;
        for (int i = 0; i < times; i++) {
            int maxValIdx = findMax(unsortedArray, endIdx);
            if (endIdx != maxValIdx){
                exchange(unsortedArray,endIdx,maxValIdx);
            }
            endIdx--;
        }

        return unsortedArray;
    }

    private int findMax(int[] unsortedArray, int endIdx) {
        // 从unsortedArray[0，endIdx] 找出最大值的idx
        int maxVal = Integer.MIN_VALUE;
        int maxIdx = -1;
        for (int i = 0; i <= endIdx; i++) {
            if (unsortedArray[i] > maxVal){
                maxVal = unsortedArray[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

}
