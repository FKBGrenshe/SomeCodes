package com.baseDataStructure.GraphStructure;

import com.baseDataStructure.GraphStrucuture.Edge;
import com.baseDataStructure.GraphStrucuture.Vertex;
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
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b), new Edge(c));
        b.edges = List.of(new Edge(c));
        c.edges = List.of(new Edge(d));

        draw.printGraph(List.of(a,b,c,d));
    }

}
