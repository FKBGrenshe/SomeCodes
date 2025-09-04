package com.baseDataStructure.Range;

import static com.baseDataStructure.Range.SortedUtil.exchange;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 冒泡排序
 */
public class BubbleSort {

    int[] sort(int[] unsortedArray){

        int sortRange = unsortedArray.length;
        int lastTimeExchangeIdx = -1;
        int changeTimes = -1;

        while (changeTimes != 0){
            changeTimes = 0;
            for (int i = 0; i < sortRange-1; i++) {
                if (unsortedArray[i] > unsortedArray[i+1]){
                    exchange(unsortedArray,i,i+1);
                    lastTimeExchangeIdx = i+1;
                    changeTimes = 1;
                }
            }
            sortRange = Math.min(sortRange,lastTimeExchangeIdx);
        }
        return unsortedArray;
    }

}
