package com.baseDataStructure.HeapStructure;

import org.junit.jupiter.api.Test;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-11
 * @Description: 大顶堆测试
 */
public class MaxHeapTest {


     @Test
    public void buildMaxHeapTest(){

         int[] arr =new int[] {
                 1,2,3,4,5,6,7
         };
         MaxHeap maxHeap = new MaxHeap(arr);
         System.out.println(maxHeap.toString());
         maxHeap.peek();


     }


}
