package com.baseDataStructure.GraphStructure;

import com.baseDataStructure.GraphStrucuture.Edge;
import com.baseDataStructure.GraphStrucuture.Prim;
import com.baseDataStructure.GraphStrucuture.Vertex;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-12
 * @Description: 图-最小生成树算法测试
 */
public class GraphBuildMinWeightTreeTest {

    @Test
    void PrimTest(){
        List<Vertex> graph = buildGraph();
        Prim.prim(graph,graph.get(0));
    }



    private static List<Vertex> buildGraph(){
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 4), new Edge(v4, 1));
        v2.edges = List.of(new Edge(v1, 2), new Edge(v4, 3), new Edge(v5, 10));
        v3.edges = List.of(new Edge(v1, 4), new Edge(v4, 2), new Edge(v6, 5));
        v4.edges = List.of(new Edge(v1, 1), new Edge(v2, 3), new Edge(v3, 2),
                new Edge(v5, 7), new Edge(v6, 8), new Edge(v7, 4));
        v5.edges = List.of(new Edge(v2, 10), new Edge(v4, 7), new Edge(v7, 6));
        v6.edges = List.of(new Edge(v3, 5), new Edge(v4, 8), new Edge(v7, 1));
        v7.edges = List.of(new Edge(v4, 4), new Edge(v5, 6), new Edge(v6, 1));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);


        return graph;

    }


}
