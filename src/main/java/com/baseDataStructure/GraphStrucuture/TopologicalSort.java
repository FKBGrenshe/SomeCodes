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

    /**
     * 拓扑排序 -- 最后会检测该图是否存在环
     * @param graph
     */
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

    /**
     * 基于深度优先搜索DFS与栈的拓扑排序
     */
    public static void searchByDFS(List<Vertex> graph){

        // 准备一个栈，保存拓扑排序的顶点顺序
        LinkedList<String> stack = new LinkedList<>();

        // 从入度为0的顶点开始深度优先搜索
        for (Vertex vertex : graph) {
            DFS4TopoSort(vertex,stack);
        }

        System.out.println(stack);

    }

    private static void DFS4TopoSort(Vertex vertex, LinkedList<String> stack){
        if (vertex.visitStatus == 2){
            // 该顶点已经被访问过了
            return;
        }

        // 找到相邻顶点，再次DFS
        if (vertex.edges != null && !vertex.edges.isEmpty()){
            for (Edge edge : vertex.edges) {
                DFS4TopoSort(edge.linked,stack);
            }
        }

        // 将当前顶点压栈
        vertex.setVisitStatus(2);
        stack.push(vertex.name);


    }


}
