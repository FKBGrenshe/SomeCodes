package com.baseJava.Collections.Map.HashMap;

import java.util.HashMap;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-28
 * @Description:
 */
public class hashMapTest {

    public static void main(String[] args) {

        // 非2的幂次方初始容量大小的初始化

        HashMap<Integer, Integer> map = new HashMap<>(3, 0.75f);

        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);

        map.forEach( (k, v) -> System.out.println("{k,v}"));


    }

}
