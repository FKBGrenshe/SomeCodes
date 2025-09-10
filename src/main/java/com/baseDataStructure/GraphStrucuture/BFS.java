package com.baseDataStructure.GraphStrucuture;

import com.baseDataStructure.QueueStructure.LinkedListQueue;
import com.baseDataStructure.QueueStructure.Queue;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 广度优先搜索-BreadFirstSearch
 */
public class BFS {

    public static void search(Vertex vertex){

        Queue<Vertex> vertexQueue = new LinkedListQueue<>();
        vertexQueue.offer(vertex);

        while (!vertexQueue.isEmpty()){
            Vertex polled = vertexQueue.poll();
            polled.visited = true;
            System.out.println(polled.name);

            if (polled.edges != null && !polled.edges.isEmpty()){
                for (Edge edge : polled.edges) {
                    if (!edge.linked.visited){
                        vertexQueue.offer(edge.linked);
                        edge.linked.visited = true;
                    }
                }
            }

        }

    }

}
