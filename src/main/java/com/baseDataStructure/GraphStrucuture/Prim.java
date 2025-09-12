package com.baseDataStructure.GraphStrucuture;

import java.util.*;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-12
 * @Description: 最小生成树算法 - prim - 复用Dijsktra单源最短路径算法
 */
public class Prim {

    public static void prim(List<Vertex> graph, Vertex source){

        source.dist = 0;

        // 使用小顶堆队列来存储为访问过的顶点
        PriorityQueue<Vertex> unVisitedQueue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        for (Vertex vertex : graph) {
            unVisitedQueue.offer(vertex);
        }

        while (!unVisitedQueue.isEmpty()){
            // 每次从队列中取出距离最小的
            Vertex minDistVertex = unVisitedQueue.peek();
            // 更新其所连接的路径
            if (minDistVertex.edges != null && !minDistVertex.edges.isEmpty()){
                for (Edge edge : minDistVertex.edges) {
                    if (!edge.linked.visited && edge.linked.dist > edge.weight){
                        edge.linked.dist = edge.weight;
                        edge.linked.prev = minDistVertex;
                        // 对队列进行更新
                        unVisitedQueue.remove(edge.linked);
                        unVisitedQueue.offer(edge.linked);
                    }
                }
            }
            // 从未访问顶点集合中删除当前顶点
            unVisitedQueue.poll();
            minDistVertex.visited = true;

        }

        for (Vertex vertex : graph) {
            System.out.println(vertex + "<-" + vertex.prev);
        }

    }

}
