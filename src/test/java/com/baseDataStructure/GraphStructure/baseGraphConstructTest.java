package com.baseDataStructure.GraphStructure;

import com.baseDataStructure.GraphStrucuture.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import com.baseDataStructure.GraphStrucuture.GraphUtil.*;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 创建图测试
 */
public class baseGraphConstructTest {

    @Test
    void createAGraph(){


        draw.printGraph(createAGraphAndReturn());
    }


    @Test
    void DFStest(){
        List graph = createAGraphAndReturn();
        DFS.search((Vertex) graph.get(0));
    }

    @Test
    void BFStest(){
        List graph = createAGraphAndReturn();
        BFS.search((Vertex) graph.get(0));
    }

    @Test
    void TopoSortTest(){
        List<Vertex> graph = createAGraphAndReturn();
        TopologicalSort.search(graph);
    }


    @Test
    void TopoSortByDFSTest(){
        List<Vertex> graph = createAGraphAndReturn();
        TopologicalSort.searchByDFS(graph);
    }



    List<Vertex> createAGraphAndReturn(){
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b), new Edge(c));
        b.edges = List.of(new Edge(d));
        c.edges = List.of(new Edge(d));

        return List.of(a,b,c,d);
    }

}
