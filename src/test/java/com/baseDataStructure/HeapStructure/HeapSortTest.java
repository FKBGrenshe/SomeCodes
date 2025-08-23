package com.baseDataStructure.HeapStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-11
 * @Description: 堆排序测试
 */
public class HeapSortTest {

    @Test
    public void test(){

        int[] arr = {3, 4, 5, 2, 1, 8, 1, 7, 9};
        int[] sortByHeap = new HeapSort().sortByHeap(arr);

        System.out.println(Arrays.toString(sortByHeap));

//        Assertions.assertEquals(List.of(1,2,3,4,5,6,7,8,9),sortByHeap);

    }


}
