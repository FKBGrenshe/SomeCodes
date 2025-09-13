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
    int[] size; // 记录每个集合的顶点个数

    public DisjoinSet(int size){
        s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < s.length; i++) {
            // 一开始，每个顶点仅与自己有关
            s[i] = i;
            this.size[i] = 1;
        }
    }

    // 找到老大索引
    public int find(int x){
        if (x == s[x]){
            // 当前节点的老大就是老大索引
            return x;
        } else{
            // 当前节点的老大不是老大索引 -- 寻找老大，并将自身也更新为老大，然后返回老大
            return s[x] = find(s[x]);
        }
    }


    /**
     * 合并 -- 让两个集合“相交”，即选出新老大，x，y是原老大索引
     * @param x
     * @param y
     */
    public void union(int x, int y){

        // 让集合中顶点个数最多的成为新老大
        int boss = size[y] > size[x] ? y : x;
        // 更新老大集合的顶点个数
        size[boss] = size[y] + size[x];
        // 让失败的老大指向新老大
        s[y] = boss;
        s[x] = boss;
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
