package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Graph;

/**
 * @Author: xhl
 * @Date: 2026-06-10 21:40
 * @Description: 通用图数据结构模板
 */
import java.util.*;

/**
 * 通用图数据结构模板 (基于邻接表)
 * @param <T> 顶点的数据类型
 */
public class Graph<T> {

    // 使用 HashMap 将每个顶点映射到其邻接列表
    private final Map<T, List<T>> adjList;
    // 标识图是否有向，若 false 则为无向图
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.adjList = new HashMap<>();
    }

    /** 添加顶点到图中 */
    public void addVertex(T vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    /** 添加边到图中 */
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjList.get(source).add(destination);
        // 如果是无向图，需要双向添加
        if (!directed) {
            adjList.get(destination).add(source);
        }
    }

    /** 删除顶点及其所有关联的边 */
    public void removeVertex(T vertex) {
        if (!adjList.containsKey(vertex)) return;
        adjList.remove(vertex);
        // 清除其他顶点邻接表中对该顶点的引用
        for (List<T> neighbors : adjList.values()) {
            neighbors.remove(vertex);
        }
    }

    /** 删除指定的边 */
    public void removeEdge(T source, T destination) {
        if (adjList.containsKey(source)) {
            adjList.get(source).remove(destination);
        }
        if (!directed && adjList.containsKey(destination)) {
            adjList.get(destination).remove(source);
        }
    }

    /** 获取某个顶点的邻接顶点列表 */
    public List<T> getNeighbors(T vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }

    /** 深度优先搜索 (DFS) */
    public List<T> dfs(T start) {
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        dfsHelper(start, visited, result);
        return result;
    }

    private void dfsHelper(T vertex, Set<T> visited, List<T> result) {
        visited.add(vertex);
        result.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    /** 广度优先搜索 (BFS) */
    public List<T> bfs(T start) {
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.add(current);
            for (T neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }
}