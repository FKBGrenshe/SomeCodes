package com.baseDataStructure.GraphStrucuture.GraphUtil;
import com.baseDataStructure.GraphStrucuture.Edge;
import com.baseDataStructure.GraphStrucuture.Vertex;

import java.util.*;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-10
 * @Description: 将图的形状画出来
 */
public final class draw {

    private draw() {} // 工具类不需要实例化

    /**
     * 将图（顶点集合）格式化为邻接表字符串。
     * @param vertices 顶点集合（例如：Arrays.asList(v1, v2, v3)）
     * @return 格式化后的字符串
     */
    public static String toAdjacencyList(Collection<Vertex> vertices) {
        if (vertices == null || vertices.isEmpty()) {
            return "(empty graph)";
        }

        StringBuilder sb = new StringBuilder();
        // 为了输出稳定性，按名称排序（若名称为 null 则放最后）
        List<Vertex> list = new ArrayList<>(vertices);
        list.sort(Comparator.comparing(
                v -> v.getName() == null ? "~" : v.getName()
        ));

        for (Vertex v : list) {
            String name = v.getName() == null ? "(null)" : v.getName();
            sb.append(name).append(" -> ");

            List<Edge> edges = v.edges;
            if (edges == null || edges.isEmpty()) {
                sb.append("∅");
            } else {
                // 将每条边格式化：目标名(权重)；权重为1时可省略
                List<String> parts = new ArrayList<>();
                for (Edge e : edges) {
                    Vertex to = (e == null ? null : e.linked);
                    String toName = (to == null || to.getName() == null) ? "(null)" : to.getName();

                    int w = (e == null ? 1 : e.weight); // 注意这里使用的是你类里的字段名 weight
                    String piece = (w <= 1) ? toName : (toName + "(" + w + ")");
                    parts.add(piece);
                }
                sb.append(String.join(", ", parts));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 直接打印图的邻接表表示。
     */
    public static void printGraph(Collection<Vertex> vertices) {
        System.out.print(toAdjacencyList(vertices));
    }
}