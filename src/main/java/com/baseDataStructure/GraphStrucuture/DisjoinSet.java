package com.baseDataStructure.GraphStrucuture;

import java.util.Arrays;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-12
 * @Description: 并查集
 *
 * 数组中每个索引对应一个图的顶点，索引中的元素对应与其有关系的顶点
 *
 */
public class DisjoinSet {

    int[] s;

    public DisjoinSet(int size){
        s = new int[size];
        for (int i = 0; i < s.length; i++) {
            // 一开始，每个顶点仅与自己有关
            s[i] = i;
        }
    }

    // 找到老大索引
    public int find(int x){
        if (x == s[x]){
            // 当前节点的老大就是老大索引
            return x;
        } else{
            // 当前节点的老大不是老大索引
            return find(s[x]);
        }
    }


    /**
     * 合并 -- 让两个集合“相交”，即选出新老大，x，y是原老大索引
     * @param x
     * @param y
     */
    public void union(int x, int y){
        s[y] = x;
    }

    @Override
    public String toString() {
        return "DisjoinSet{" +
                "s=" + Arrays.toString(s) +
                '}';
    }

    /*public static void main(String[] args) {

    }*/
}
