package com.baseDataStructure.GraphStrucuture;

import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-11
 * @Description: Bellman-Ford算法-单源最短路径，解决了Dijkstra算法在面对负数边权重时工作异常的问题
 */
public class BellmanFord {


    public static void bellmanFord(List<Vertex> graph, Vertex source){
        source.dist = 0;

        int size = graph.size();
        for (int i = 0; i < size; i++) {
            // 每次循环处理所有顶点的所有边
            for (Vertex startNode : graph) {
                // 遍历当前顶点所有边
                if (startNode.edges!= null && !startNode.edges.isEmpty()){
                    for (Edge edge : startNode.edges) {
                        // 处理当前边
                        Vertex endNode = edge.linked;
                        if (startNode.dist != Integer.MAX_VALUE){
                            // 当起始点不是无穷大时，更新才有意义
                            if (i == size-1){
                                // 检测负环
                                if (endNode.dist > startNode.dist+edge.weight) {
                                    throw new RuntimeException("出现负环");
                                }
                            }
                            endNode.dist = Math.min(endNode.dist, startNode.dist+edge.weight);
                            endNode.prev = startNode;
                        }
                    }
                }
            }
        }

        for (Vertex vertex : graph) {
            System.out.println(vertex + (vertex.prev != null ? vertex.prev.name : "null"));
        }

    }

}
