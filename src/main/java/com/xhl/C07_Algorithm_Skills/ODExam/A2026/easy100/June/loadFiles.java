package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.June;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:08
 * @Description:    0603-返回所有加载的AGENTS.md文件ID列表-100分
 */
public class loadFiles {
    static void main() {
        int[] idList = {1, 2, 3, 4, 5}; // 文件自身ID列表
        int[] parentList = {0, 0, 1, 2, 2}; // 文件对应的父文件ID列表
        int loadId = 2; // 此次需要加载的某个md文件ID
        loadFiles lf = new loadFiles();
        List<Integer> list = lf.loadFiles(idList, parentList, loadId);

        System.out.println(list);
    }

    //
    public List<Integer> loadFiles1(int[] idList, int[] parentList, int loadId) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < idList.length; i++) {
            if (idList[i] == loadId) {
                res.add(i+1);
            }
            if (parentList[i] == loadId) {
                res.add(i+1);
            }
        }
        Collections.sort(res);
        return res;
    }

    // 多叉树（或图）的向下遍历  数据结构构建、BFS 模板代码的编写、以及边界条件（防环）的处理
    public List<Integer> loadFiles(int[] idList, int[] parentList, int loadId) {
        // 1. 【数据预处理】构建父子关系映射表（空间换时间）
        // 题目给出的是两个平行数组，为了快速找到某个文件的所有子文件，
        // 我们需要将其转换为 HashMap 结构：Key是父文件ID，Value是该父文件下的子文件ID列表。
        Map<Integer, List<Integer>> children = new HashMap<>();

        for (int i = 0; i < idList.length; i++) {
            int pid = parentList[i]; // 获取当前文件的父文件ID
            int cid = idList[i]; // 获取当前文件自身的ID
            // 如果Map中还没有这个父文件，就新建一个ArrayList；然后把当前子文件ID加进去。
            // 这一步将后续查找子文件的时间复杂度从 O(N) 降维到了 O(1)。
            children.computeIfAbsent(pid, k -> new ArrayList<>()).add(cid);
        }
        // 2. 【BFS 广度优先搜索】从指定的 loadId 开始，向下遍历加载所有子孙文件
        Set<Integer> resultSet = new HashSet<>(); // 记录需要加载的文件ID，使用Set天然去重并防止死循环
        Queue<Integer> queue = new LinkedList<>();// 使用队列实现BFS的“先进先出”特性
        queue.add(loadId);// 将此次需要加载的根节点ID加入队列
        while (!queue.isEmpty()) { // 从队首弹出一个文件ID进行处理
            int node = queue.poll();
            // 检查该文件是否已经被处理过（防止重复加载和死循环）
            if (!resultSet.contains(node)) {
                resultSet.add(node); // 标记为已处理，加入结果集
                // 如果当前文件在映射表中有子文件，将它们全部加入队列，等待下一层遍历
                if (children.containsKey(node)) {
                    queue.addAll(children.get(node));
                }
            }
        }
        // 3. 【结果处理】转换为列表并按数值从小到大排序
        // 题目要求“按从小到大的数值顺序返回”，所以这里做了一步排序
        List<Integer> resultList = new ArrayList<>(resultSet);
        Collections.sort(resultList);
        return resultList;
    }
}