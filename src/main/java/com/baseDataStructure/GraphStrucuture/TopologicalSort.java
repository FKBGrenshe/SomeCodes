package com.baseDataStructure.GraphStrucuture;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 拓扑排序
 */
public class TopologicalSort {

    public static void search(List<Vertex> graph){
        // 统计每个顶点的入度
        for (Vertex vertex : graph) {
            if (vertex.edges != null && !vertex.edges.isEmpty()){
                for (Edge edge : vertex.edges) {
                    edge.linked.inDegree++;
                }
            }
        }

        // 将入读为0的顶点加入队列
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0){
                queue.offer(vertex);
            }
        }

        // 队列中不断移除顶点，每移除一个顶点，将它指向的顶点入度-1，若减到0则入队
        // 真正的拓扑排序的存储队列
        List<String> sortedList = new ArrayList<>();
        while (!queue.isEmpty()){
            Vertex polled = queue.poll();
            System.out.println(polled.name);
            sortedList.add(polled.name);
            if (polled.edges != null && !polled.edges.isEmpty()){
                for (Edge edge : polled.edges) {
                    edge.linked.inDegree--;
                    if (edge.linked.inDegree == 0){
                        queue.offer(edge.linked);
                    }
                }
            }
        }

        if (graph.size() != sortedList.size()){
            System.out.println("该图存在环！");
        }
    }


}
