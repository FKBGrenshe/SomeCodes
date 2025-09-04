package com.baseDataStructure.Range;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 工具类
 */
public class SortedUtil {
    public static void exchange(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
