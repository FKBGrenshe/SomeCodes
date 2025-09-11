package com.baseDataStructure.GraphStrucuture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-11
 * @Description: 迪杰斯特拉最短路径算法
 */
public class Dijkstra {

    /**
     * 迪杰斯特拉最短路径算法
     * @param graph 原图
     * @param source 起始顶点
     */
    public static void dijkstra(List<Vertex> graph, Vertex source){

        // 1. 放入所有未访问的顶点的集合
        ArrayList<Vertex> unVisitedList = new ArrayList<>(graph);
        // 2. 为每个顶点分配临时距离值（起始顶点设为0，其他顶点不用动因为初始化已经是Integer.MAX_VALUE）
        source.dist = 0;

        while (!unVisitedList.isEmpty()){
            // 3. 选择最小临时距离的未访问顶点
            Vertex curNode = chooseMinDistVertex(unVisitedList);
            // 4. 更新其连通的顶点的路径权重
            updateNeighborDist(curNode);
            // 5. 从未访问顶点集合中删除该顶点
            unVisitedList.remove(curNode);
            curNode.visited = true;
        }

        // 所有顶点都处理完成了 -> 从初始节点到图中所有节点的最短距离已经更新到了图中
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + ": " + vertex.dist + " prev: " + (vertex.prev!=null?vertex.prev.name : "null"));
        }

    }

    private static void updateNeighborDist(Vertex curNode) {
        if (curNode.edges!= null && ! curNode.edges.isEmpty()){
            for (Edge edge : curNode.edges) {
                if (!edge.linked.visited){
                    // 只更新未访问的（连通节点）邻居
                    //edge.linked.dist = Math.min(curNode.dist+edge.weight, edge.linked.dist);
                    if (edge.linked.dist > curNode.dist+ edge.weight){
                        edge.linked.dist = curNode.dist + edge.weight;
                        edge.linked.prev = curNode;
                    }
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(List<Vertex> graph) {
        // 假定队首顶点为最小临时距离值顶点
        Vertex minDistNod = graph.get(0);

        for (Vertex vertex : graph) {
            minDistNod = minDistNod.dist < vertex.dist ? minDistNod : vertex;
        }

        return minDistNod;

    }


    /**
     * 基于优先级队列的迪杰斯特拉最短路径算法
     *  使用小顶堆作为存储未访问顶点的集合，因此在步骤3寻找最小临时距离的未访问顶点时就不用遍历寻找了
     * @param graph 原图
     * @param source 起始顶点
     */
    public static void dijkstraWithPriorityQueue(List<Vertex> graph, Vertex source){

        // 1. 放入所有未访问的顶点的集合 : 优先级队列默认小顶堆
        PriorityQueue<Vertex> unVisitedList = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        // 2. 为每个顶点分配临时距离值（起始顶点设为0，其他顶点不用动因为初始化已经是Integer.MAX_VALUE）
        source.dist = 0;
        // 将所有顶点加入队列
        for (Vertex vertex : graph) {
            unVisitedList.offer(vertex);
        }

        while (!unVisitedList.isEmpty()){
            System.out.println(unVisitedList);
            // 3. 选择最小临时距离的未访问顶点
            Vertex curNode = unVisitedList.peek();
            // 4. 更新其连通的顶点的路径权重
            updateNeighborDistWithPriorityQueue(curNode, unVisitedList);
            // 5. 从未访问顶点集合中删除该顶点
            unVisitedList.poll();
            curNode.visited = true;
        }

        // 所有顶点都处理完成了 -> 从初始节点到图中所有节点的最短距离已经更新到了图中
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + ": " + vertex.dist + " prev: " + (vertex.prev!=null?vertex.prev.name : "null"));
        }

    }

    private static void updateNeighborDistWithPriorityQueue(Vertex curNode, PriorityQueue<Vertex> queue) {
        if (curNode.edges!= null && ! curNode.edges.isEmpty()){
            for (Edge edge : curNode.edges) {
                if (!edge.linked.visited){
                    // 只更新未访问的（连通节点）邻居
                    if (edge.linked.dist > curNode.dist+ edge.weight){
                        // 找到了更小的距离
                        edge.linked.dist = curNode.dist + edge.weight;
                        edge.linked.prev = curNode;
                        // 将堆中的顶点距离进行更新
                        queue.remove(edge.linked);
                        queue.offer(edge.linked);
                    }
                }
            }
        }
    }


}
