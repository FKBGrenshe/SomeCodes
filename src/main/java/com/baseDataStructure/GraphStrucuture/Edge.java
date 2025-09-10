package com.baseDataStructure.GraphStrucuture;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 边 - 邻接表的边对象结构
 *  有向图结构，边属于哪个顶点，哪个顶点就是起点；边的属性中vertex为边的终点
 */
public class Edge {

    Vertex linked;
    // 边权重
    int weeight;

    public Edge(Vertex linked) {
        this(linked,1);
    }

    public Edge(Vertex linked, int weeight) {
        this.linked = linked;
        this.weeight = weeight;
    }
}
