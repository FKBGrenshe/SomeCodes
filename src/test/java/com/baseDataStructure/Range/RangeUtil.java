package com.baseDataStructure.Range;

import java.util.Random;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-04
 * @Description: 工具类
 */
public class RangeUtil {

    static int[] newUnsortedArray(int length){
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Random random = new Random();
        int changeTimes = random.nextInt(0,length);
        int changeIdxJ;
        int changeIdxI;
        for (int i = 0; i < changeTimes; i++) {
            changeIdxI = random.nextInt(0,length-1);
            while ((changeIdxJ = random.nextInt(0,length-1)) == changeIdxI){}
            int temp = array[changeIdxI];
            array[changeIdxI] = array[changeIdxJ];
            array[changeIdxJ] = temp;
        }
        return array;
    }


    static boolean checkIfSorted(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }

}
