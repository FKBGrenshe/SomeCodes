package com.baseDataStructure.GraphStrucuture;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-12
 * @Description: 最小生成树- Kruskal算法
 */
public class Kruskal {

    public static class KruskalEdge implements Comparable<KruskalEdge>{

        //
        List<Vertex> vertices;
        // 顶点集合中的索引值所代表的顶点，是当前边的起点
        int start;
        // 顶点集合中的索引值所代表的顶点，是当前边的终点
        int end;

        int weight;

        public KruskalEdge(List<Vertex> vertices, int start, int end, int weight) {
            this.vertices = vertices;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public KruskalEdge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(KruskalEdge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
        }

        public List<Vertex> getVertices() {
            return vertices;
        }
    }

    /**
     * kruskal算法
     * @param size 顶点个数
     * @param queue 边集合
     */
    public static void kruskal(int size, PriorityQueue<KruskalEdge> queue){
        List<KruskalEdge> list = new ArrayList<>();
        DisjoinSet set = new DisjoinSet(size);
        // 最小生成树的边数 = 顶点个数-1 （e.g. 要将3个顶点联通只需要2条边）
        while (list.size() < size-1){
            // 拿到当前图中最小的边
            KruskalEdge kruskalEdge = queue.poll();

            int i = set.find(kruskalEdge.start);
            int j = set.find(kruskalEdge.end);
            if (i != j){
                // 如果这个顶点的并查集“老大”不相等，说明这两个顶点并未联通
                // 将该条边加入结果集
                list.add(kruskalEdge);
                // 将两个顶点进行联通
                set.union(i,j);
            }
        }

        for (KruskalEdge kruskalEdge : list) {
            System.out.println(kruskalEdge);
        }

    }


}
