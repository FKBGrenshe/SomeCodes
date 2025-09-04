package com.baseDataStructure.Range;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description:
 */
public class SortTest {


    @Test
    void BubbleSortTest(){
        int[] unsortedArray = RangeUtil.newUnsortedArray(100);
        System.out.println(Arrays.toString(unsortedArray));

        BubbleSort bubbleSort = new BubbleSort();
        int[] sortedArray = bubbleSort.sort(unsortedArray);

        System.out.println("array is sorted?"+RangeUtil.checkIfSorted(sortedArray));
        System.out.println(Arrays.toString(unsortedArray));
    }


    @Test
    void SelectionSortTest(){
        int[] unsortedArray = RangeUtil.newUnsortedArray(100);
        System.out.println(Arrays.toString(unsortedArray));

        SelectionSort selectionSort = new SelectionSort();
        int[] sortedArray = selectionSort.sort(unsortedArray);

        Assertions.assertTrue(RangeUtil.checkIfSorted(sortedArray));

        System.out.println("array is sorted?"+RangeUtil.checkIfSorted(sortedArray));
        System.out.println(Arrays.toString(unsortedArray));
    }

}
