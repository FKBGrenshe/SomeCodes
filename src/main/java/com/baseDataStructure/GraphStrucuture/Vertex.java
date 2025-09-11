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

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
