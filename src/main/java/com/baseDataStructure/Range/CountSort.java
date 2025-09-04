package com.baseDataStructure.Range;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 计数排序
 */
public class CountSort {

    public int[] sort(int[] nums) {
        int[] counts = new int[100002];
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i] + 50000]++;
        }

        int i = 0;
        for (int j = 0; j < counts.length; j++) {
            if (counts[j] != 0){
                for (int i1 = 0; i1 < counts[j]; i1++) {
                    nums[i++] = j-50000;
                }
            }
        }
        return nums;

    }

}
