package com.baseDataStructure.Range.MergeSort;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 归并排序 递归 自上而下
 */
public class MergeSortTopDown {



    int[] sort(int[] array){
        int[] temp = new int[array.length];
        split(array,0,array.length-1, temp);
        return array;
    }

    private void split(int[] array, int left, int right, int[] temp){
        // 治：此处有序了
        if (left == right){
            // 仅有一个数据，肯定是有序的，返回
            return;
        }
        // 分
        int middle = (left+right)>>>1;
        split(array,left,middle, temp);
        split(array,middle+1,right, temp);

        // 合 - 合并两个有序数组
        merge(array, left, middle, middle+1, right, temp);
        System.arraycopy(temp, left, array, left, right - left + 1);

    }

    /**
     * 合并数组中两个有序部分
     * @param a1 原始数组
     * @param i 第一个有序范围
     * @param iEnd
     * @param j 第二个有序范围
     * @param jEnd
     * @param a2 临时数组（与原始数组大小一致）
     */
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

}
