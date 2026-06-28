package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.BinaryTree;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-07 18:12
 * @Description: 二 叉 树  的 每层 平均值 一维动态规划
 */
public class averageOfLevels {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        averageOfLevels aoL= new averageOfLevels();
        List<Double> doubles = aoL.BSaverageOfLevels(root);
        System.out.println("二叉树的 层 平均值 深度优先搜索:"+ doubles);

        averageOfLevels aoL2 = new averageOfLevels();
        List<Double> doubles2 = aoL2.BSaverageOfLevels(root);
        System.out.println("二叉树广度优先搜索 方法计算平均值:"+ doubles2);

    }
    // 方法一: 深度优先搜索
    public List<Double> BSaverageOfLevels(TreeNode root) {
        // 用来存储 二叉树的每一层的节点个数
        List<Integer> counts = new ArrayList<Integer>();
        // 用来存储 二叉树的每一层的节点 的和
        List<Double> sums = new ArrayList<Double>();
        // 深度优先 遍历
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<Double>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }
    // 深度优先搜索 dfs
    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }

    // 方法二：广度优先搜索
    public List<Double> GaverageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<Double>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }
}
