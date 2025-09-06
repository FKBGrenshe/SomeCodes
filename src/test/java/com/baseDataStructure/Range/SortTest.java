package com.baseDataStructure.Range;

import com.baseDataStructure.Range.MergeSort.MergeSortTopDown;
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

    @Test
    void HeapSortTest(){
        int[] unsortedArray = RangeUtil.newUnsortedArray(100);
        System.out.println(Arrays.toString(unsortedArray));

        HeapSort heapSort = new HeapSort();
        int[] sortedArray = heapSort.sort(unsortedArray);

        Assertions.assertTrue(RangeUtil.checkIfSorted(sortedArray));

        System.out.println("array is sorted?"+RangeUtil.checkIfSorted(sortedArray));
        System.out.println(Arrays.toString(unsortedArray));
    }

    @Test
    void InsertionSortTest(){
        int[] unsortedArray = RangeUtil.newUnsortedArray(100);
        System.out.println(Arrays.toString(unsortedArray));

        InsertionSort insertionSort = new InsertionSort();
        int[] sortedArray = insertionSort.sort(unsortedArray,0);

        Assertions.assertTrue(RangeUtil.checkIfSorted(sortedArray));

        System.out.println("array is sorted?"+RangeUtil.checkIfSorted(sortedArray));
        System.out.println(Arrays.toString(unsortedArray));
    }

    @Test
    void ShellSortTest(){
        int[] unsortedArray = RangeUtil.newUnsortedArray(100);
        System.out.println(Arrays.toString(unsortedArray));

        ShellSort shellSort = new ShellSort();
        int[] sortedArray = shellSort.sort(unsortedArray);

        Assertions.assertTrue(RangeUtil.checkIfSorted(sortedArray));

        System.out.println("array is sorted?"+RangeUtil.checkIfSorted(sortedArray));
        System.out.println(Arrays.toString(unsortedArray));
    }

    @Test
    void MergeSortTest(){
        int[] unsortedArray = RangeUtil.newUnsortedArray(10);
        System.out.println(Arrays.toString(unsortedArray));

        MergeSortTopDown mergeSort = new MergeSortTopDown();
        int[] sortedArray = mergeSort.sort(unsortedArray);


        System.out.println("array is sorted?"+RangeUtil.checkIfSorted(sortedArray));
        System.out.println(Arrays.toString(unsortedArray));
        Assertions.assertTrue(RangeUtil.checkIfSorted(sortedArray));
    }

}
