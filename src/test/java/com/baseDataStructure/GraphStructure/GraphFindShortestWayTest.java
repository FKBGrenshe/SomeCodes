package com.baseDataStructure.GraphStructure;

import com.baseDataStructure.GraphStrucuture.Dijkstra;
import com.baseDataStructure.GraphStrucuture.Edge;
import com.baseDataStructure.GraphStrucuture.Vertex;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-11
 * @Description: 图寻找最短路径测试
 */
public class GraphFindShortestWayTest {


    @Test
    void DijkstraTest(){
        List<Vertex> graph = buildGraph();
        Dijkstra.dijkstra(graph,graph.get(0));
    }

    @Test
    void DijkstraWithPrioirtyQueueTest(){
        List<Vertex> graph = buildGraph();
        Dijkstra.dijkstraWithPriorityQueue(graph,graph.get(0));
    }





    private static List<Vertex> buildGraph(){
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");


        v1.edges = List.of(
                new Edge(v3, 9),
                new Edge(v2,7),
                new Edge(v6,14)
        );

        v2.edges = List.of(
                new Edge(v4,15)
        );

        v3.edges = List.of(
                new Edge(v4,11),
                new Edge(v6,2)
        );

        v4.edges = List.of(
                new Edge(v5,6)
        );

        v5.edges = List.of();

        v6.edges = List.of(
                new Edge(v5,9)
        );

        return List.of(v1,v2,v3,v4,v5,v6);

    }
}
