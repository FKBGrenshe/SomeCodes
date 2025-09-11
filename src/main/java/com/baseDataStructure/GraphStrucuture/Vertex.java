package com.baseDataStructure.GraphStrucuture;

import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 顶点-使用邻接表表示法的顶点对象结构
 */
public class Vertex {

    // 顶点名字
    String name;
    // 顶点的边的集合
    public List<Edge> edges;

    // 入度
    int inDegree = 0;

    // 辅助遍历属性：表明是否被访问过
    boolean visited = false;
    // 详细访问状态: 0-未被访问，1-访问中，2-访问过 （用在拓扑排序）
    int visitStatus = 0;

    // Dijkstra算法 - 距离值
    int dist = INF;
    static final Integer INF = Integer.MAX_VALUE;
    // Dijkstra算法 - 前一顶点
    Vertex prev = null;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(int visitStatus) {
        this.visitStatus = visitStatus;
    }

    @Override
    public String toString() {
        return name+"("+dist+")";
    }
}
