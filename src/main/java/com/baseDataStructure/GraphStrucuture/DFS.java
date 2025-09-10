package com.baseDataStructure.GraphStrucuture;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 深度优先搜索-DepthFirstSearch
 */
public class DFS {

    public static void search(Vertex vertex){

        if (vertex == null || vertex.visited){
            return;
        }

        System.out.println("this is:" + vertex.name);
        vertex.visited = true;

        if (vertex.edges != null && !vertex.edges.isEmpty()){
            for (Edge edge : vertex.edges) {
                search(edge.linked);
            }
        }

    }

}
