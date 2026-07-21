package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:38
 * @Description: 129. 求根节点到叶节点数字之和 ⭐⭐⭐
 *  深度优先遍历 DFS + BFS 广度优先遍历
 */
public class sumNumbers {
    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        sumNumbers sn = new sumNumbers();
        int sumNumbers = sn.sumNumbers(root);
        System.out.println("根节点到叶节点数字之和:"+sumNumbers);
    }
    /**
     * 方法一：深度优先搜索
     * */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    public int dfs(TreeNode root, int prevSum) {
        if (root == null) return 0;
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
    /**
     * 方法二：广度优先搜索 BFS
     * */
    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();

        nodeQueue.offer(root);
        numQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }
}
