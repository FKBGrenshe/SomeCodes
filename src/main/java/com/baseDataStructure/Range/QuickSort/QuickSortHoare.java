package com.baseDataStructure.Range.QuickSort;

import com.baseDataStructure.Range.SortedUtil;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-06
 * @Description: 快速排序 双边循环
 */
public class QuickSortHoare {

    public static void sort(int[] a){
        quick(a, 0, a.length-1);
    }

    private static void quick(int[] a, int left, int right){
        if (left >= right){
            return;
        }

        int p = partition(a, left, right);
        quick(a, left, p-1);
        quick(a, p+1, right);

    }

    private static int partition(int[] a, int left, int right){
        int pivotVal = a[left];
        int i = left, j = right;

        while (i < j){
            // j 从右向左 找小的
            while ( i < j && a[j] > pivotVal){
                j--;
            }
            // i 从左向右 找大的
            while ( i < j && a[i] <= pivotVal){
                i++;
            }
            // 交换位置
            SortedUtil.exchange(a, i, j);
        }
        // 将基准点元素 与 i进行交换
        SortedUtil.exchange(a, left, i);
        return i;
    }

}
