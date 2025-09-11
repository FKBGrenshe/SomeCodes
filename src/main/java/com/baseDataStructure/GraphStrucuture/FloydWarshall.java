package com.baseDataStructure.GraphStrucuture;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-11
 * @Description: Floyd-Warshall多源最短路径算法
 */
public class FloydWarshall {

    public static void floydWarshall(List<Vertex> graph){
        int size = graph.size();
        int[][] dist = new int[size][size];
        Vertex[][] prev = new Vertex[size][size];

        // 1.矩阵初始化
        for (int i = 0; i < size; i++) {
            // 每一行的顶点
            Vertex v = graph.get(i);
            // 将边的list集合转成map，进行快速判断
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            for (int j = 0; j < size; j++) {
                // 每一列的顶点
                Vertex u = graph.get(j);
                dist[i][j] = v == u ? 0 : map.getOrDefault(u,Integer.MAX_VALUE);
                if (dist[i][j] != 0 && dist[i][j] != Integer.MAX_VALUE){
                    prev[i][j] = v;
                }
                /*if (v == u){
                    // ij是否为同一节点
                    dist[i][j] = 0;
                }else {
                    // 是否连通
                    dist[i][j] = map.getOrDefault(u,Integer.MAX_VALUE);
                }*/
            }
        }

        // 2. 能否“借路”到达其他顶点
        // 最外层循环为算法遍历次数 -- 有多少个节点，遍历多少次
        /*  dist[i][j] -- i+1,j+1
            dist[1][0] -- 第2行第1列 -- 第2个顶点到第1个顶点 --第2个顶点借助第1个顶点
         */
        for (int k = 0; k < size; k++) {
            // k --借助第“k”个顶点，到其他顶点
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // 第i+1个顶点，借助第k+1个顶点，到第j+1个顶点
                    // dist[i][k] + dist[k][j]
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE){
                        dist[i][j] = Math.min(dist[i][j],dist[i][k] + dist[k][j]);
                        if (dist[i][j] == dist[i][k] + dist[k][j]){
                            // 更新prev路径数组
                            prev[i][j] = prev[k][j];
                        }
                    }
                }
            }
            print2Dmatrix(dist);
        }


        print2Dmatrix(dist);

    }

    private static void print2Dmatrix(int[][] matrix){
        System.out.println("---------------------");
        for (int[] row : matrix) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                    .map(s -> String.format("%2s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    public static void main(String[] args) {

        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3,-2));
        v2.edges = List.of(new Edge(v1,4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4,2));
        v4.edges = List.of(new Edge(v2, -1));

        List<Vertex> graph = List.of(v1,v2,v3,v4);
        floydWarshall(graph);
    }

}
