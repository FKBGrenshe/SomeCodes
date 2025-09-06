package com.baseDataStructure.Range.QuickSort;

import com.baseDataStructure.Range.SortedUtil;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-06
 * @Description: 单边循环快排
 */
public class QuickSortLomuto {

    public static void sort(int[] a){
        quick(a, 0, a.length-1);
    }


    private static void quick(int[] a, int left, int right){
        if (left >= right){
            return;
        }
        int p = partition(a, left, right); // p代表基准点元素索引
        quick(a, left, p-1);
        quick(a, p+1, right);
    }

    private static int partition(int[] a, int left, int right) {

        int pval = a[right]; // 基准点元素值
        int i = left, j = left;
        // 找第一个比基准点值大的索引
        while (j < right){
            if (a[j] < pval){ // j 找到比基准点小的了，没找到 i 比基准点大的
                if (i != j){
                    SortedUtil.exchange(a, i, j);
                }
                i++;
            }
            j++;
        }
        SortedUtil.exchange(a, i, right); //将 基准点 与 i 进行交换
        return i;
    }


}
