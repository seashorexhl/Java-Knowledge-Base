package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:34
 * @Description: 寻找重复子数据-200分
 * 递归解决
 * 解题思路：
 *  1.序列化生成
 *  2.统计计数
 *  3.节点数计算
 *  4.节点筛选
 *  5.排序输出
 *  1.2.3,4,#,2,4,#,#,4
 */
public class findDuplicateSubtrees {
    static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);

        findDuplicateSubtrees fds = new findDuplicateSubtrees();
        List<String> duplicateSubtrees = fds.findDuplicateSubtrees(root);
        System.out.println(duplicateSubtrees.toString());
    }

    /**
     *  递归解决
     * */
    public List<String> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, Integer> nodeCountMap = new HashMap<>();
        dfs(root, countMap, nodeCountMap);

        List<String> result = new ArrayList<>();
        for (String serial : countMap.keySet()) {
            if (!serial.equals("#") && countMap.get(serial) >= 2) {
                result.add(serial);
            }
        }

        Collections.sort(result, (a, b) -> {
            int cntA = nodeCountMap.get(a);
            int cntB = nodeCountMap.get(b);
            if (cntA != cntB) {
                return cntB - cntA;
            }
            return a.compareTo(b);
        });
        return result;
    }
    /**
     *  深度优先遍历
     * */
    private Pair<String, Integer> dfs(TreeNode node, Map<String, Integer> countMap, Map<String, Integer> nodeCountMap) {
        if (node == null) return new Pair<>("#", 0);

        Pair<String, Integer> left = dfs(node.left, countMap, nodeCountMap);
        Pair<String, Integer> right = dfs(node.right, countMap, nodeCountMap);

        String serial = node.val + "," + left.getKey() + "," + right.getKey();
        int nodeCount = 1 + left.getValue() + right.getValue();

        countMap.put(serial, countMap.getOrDefault(serial, 0) + 1);
        nodeCountMap.putIfAbsent(serial, nodeCount);

        return new Pair<>(serial, nodeCount);
    }

    static class Pair<K, V> {
        K key;
        V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        K getKey() { return key; }
        V getValue() { return value; }
    }

}
