package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.Apirl;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 01:14
 * @Description:    项目模块依赖构建顺序规划-200分 果断放弃
 * 有向无环图（DAG）的所有合法拓扑排序
 * 1．每个合法的构建顺序作为一个结果
 * 2．多个结果按字典序排序后输出
 * 3．如果存在循环依赖（依赖成环的情况），则说明没有合法的构建顺序，返回空数组
 * 输入格式：［模块名1","模块名2","模块名N"]
 * [［依赖模块，被依赖模块］,［依赖模块，被依赖模块］,..]
 * 输出格式：输出所有合法的构建顺序，模块之间用空格分隔，按字典序排序。
 */
public class buildOrder {

    static void main(String[] args) {
        String[] modules = {"user","auth","database","api"};
        String[][] dependencies = {{"user","auth"},{"auth","database"},{"api","database"}};
        buildOrder bo = new buildOrder();
        List<String> list = bo.buildOrder(modules, dependencies);
        System.out.println(list.toString());

    }
    // 建图与入度统计 (buildOrder 上半部分)：
    // 环检测 (buildOrder 中间部分)：
    // 回溯生成所有拓扑序列 (backtrack)：
    // 结果处理：

    public List<String> buildOrder(String[] modules, String[][] dependencies) {
        // 邻接表：存储图结构，key为前置节点，value为依赖该前置节点的后继节点列表
        Map<String, List<String>> graph = new HashMap<>();
        // 记录每个节点的入度（即有多少个前置依赖未完成）
        Map<String, Integer> indegree = new HashMap<>();
        // 初始化所有节点的入度为0

        for (String m : modules) {
            indegree.put(m, 0);
        }
        // 根据依赖关系建图并更新入度
        // dep[0] 依赖 dep[1]，即 dep[1] -> dep[0]，dep[0]的入度+1

        for (String[] dep : dependencies) {
            String a = dep[0], b = dep[1];
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            indegree.put(a, indegree.get(a) + 1);
        }
        // ========== 第一步：使用 BFS (Kahn算法) 检测图中是否存在环 ==========

        Map<String, Integer> indegreeBfs = new HashMap<>(indegree);
        Queue<String> queue = new LinkedList<>();
        int count = 0;
        // 将所有入度为0的节点加入队列

        for (String m : modules) {
            if (indegreeBfs.get(m) == 0) queue.add(m);
        }
        // BFS 遍历，模拟拓扑排序过程

        while (!queue.isEmpty()) {
            String node = queue.poll();
            count++;
            // 遍历当前节点的所有后继节点，将其入度减1

            if (graph.containsKey(node)) {
                for (String nb : graph.get(node)) {
                    indegreeBfs.put(nb, indegreeBfs.get(nb) - 1);
                    // 如果后继节点入度变为0，加入队列

                    if (indegreeBfs.get(nb) == 0) queue.add(nb);
                }
            }
        }
        // 如果处理的节点数不等于总模块数，说明存在环，无法完成拓扑排序

        if (count != modules.length) return new ArrayList<>();
        // ========== 第二步：使用回溯法生成所有合法的拓扑排序序列 ==========

        List<String> result = new ArrayList<>();
        backtrack(new ArrayList<>(), new HashMap<>(indegree), graph, modules, result);
        // 对结果按字典序排序
        Collections.sort(result);
        return result;
    }
    /**
     *  回溯方法：生成所有合法的拓扑排序
     *  @param path     当前已选择的节点路径
     *  @param indegree 当前各节点的入度状态（回溯过程中会动态修改）
     *  @param graph    图的邻接表
     *  @param modules  所有模块列表
     *  @param result   存储最终结果的列表
     * */
    private void backtrack(List<String> path, Map<String, Integer> indegree,
                           Map<String, List<String>> graph, String[] modules,
                           List<String> result) {
        // 递归终止条件：路径长度等于模块总数，说明找到了一个完整的合法序列

        if (path.size() == modules.length) {
            result.add(String.join(" ", path));
            return;
        }
        // 收集当前所有入度为0的节点（即可选的前置节点）

        List<String> zeros = new ArrayList<>();
        for (String m : modules) {
            if (indegree.get(m) == 0) zeros.add(m);
        }
        // 遍历所有可选节点，进行回溯

        for (String node : zeros) {
            // 1. 做选择：将当前节点入度设为-1，标记为"已选"（防止同一层重复选择）

            indegree.put(node, -1);
            path.add(node);
            // 2. 更新状态：将当前节点所有后继节点的入度减1

            if (graph.containsKey(node)) {
                for (String nb : graph.get(node)) {
                    indegree.put(nb, indegree.get(nb) - 1);
                }
            }
            // 3. 递归进入下一层

            backtrack(path, indegree, graph, modules, result);
            // 4. 撤销选择（回溯）：恢复后继节点入度

            if (graph.containsKey(node)) {
                for (String nb : graph.get(node)) {
                    indegree.put(nb, indegree.get(nb) + 1);
                }
            }
            // 5. 撤销选择：从路径移除当前节点，并恢复其入度为0

            path.remove(path.size() - 1);
            indegree.put(node, 0);
        }
    }


}
