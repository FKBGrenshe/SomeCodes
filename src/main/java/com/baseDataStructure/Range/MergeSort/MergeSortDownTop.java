package com.baseDataStructure.Range.MergeSort;

import java.util.Arrays;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-06
 * @Description: 归并排序 非递归 自下而上
 */
public class MergeSortDownTop {


    public static void sort(int[] a1){
        int length = a1.length;
        int[] a2 = new int[length];
        int minBigger2Val = findMinBigger2Val(length);

        for (int width = 1; width <= minBigger2Val; width *= 2) {
            // 从 宽度为1的分组 进行排序
            // 每次排序结束之后，宽度变为2倍，再进行排序，直到宽度 >= 原数组

            // 【left, right】确定当前需要排序的分组的左右边界
            for (int left = 0; left < length; left += 2*width) {
                int right = Math.min(left+2*width-1, length-1);
                System.out.printf("width %d [%d, %d] %n", width, left, right);
                int middle = (right + left)/2;
                merge(a1, left, middle, middle+1, right, a2);
                System.arraycopy(a2, left, a1, left, right-left+1);
                System.out.printf(Arrays.toString(a1) + "%n");
                System.out.println("---------------------");
            }

        }

    }

    private static int findMinBigger2Val(int initialVal){
        int newVal = 1;
        while (newVal < initialVal){
            newVal  <<=  1;
            System.out.println(newVal);
        }
        return newVal;
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

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 6, 2, 5, 4, 8 ,1, 0};
        System.out.println(Arrays.toString(a));
        MergeSortDownTop.sort(a);
        System.out.println(Arrays.toString(a));
    }


}
