package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.easy;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-22 11:36
 * @Description: 112 路径总和
 */
public class hasPathSum {
    static void main() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        int targetSum = 22;


        hasPathSum hp = new hasPathSum();
        System.out.println(hp.hasPathSum(root, targetSum));

    }
    //
    public boolean hasPathSum0(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // 递归解决
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }

    // 主函数：判断是否存在从根节点到叶子节点的路径，其和等于 targetSum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 1. 边界条件：如果树是空的，直接返回 false
        if (root == null) {
            return false;
        }

        // 2. 核心逻辑：调用 DFS 递归函数
        // 初始的 sum 为 0，从根节点开始往下找
        return dfs(root, targetSum, 0);
    }

    // 辅助 DFS 函数：node(当前节点), targetSum(目标值), currentSum(当前路径累加和)
    private boolean dfs(TreeNode node, int targetSum, int currentSum) {
        // 走到空节点了，说明上一级不是叶子节点，返回 false
        if (node == null) {
            return false;
        }

        // 把当前节点的值加到路径总和里
        currentSum += node.val;

        // 判断是不是叶子节点（左右孩子都为空）
        if (node.left == null && node.right == null) {
            // 如果是叶子节点，直接判断累加和是否等于目标值
            return currentSum == targetSum;
        }

        // 如果不是叶子节点，继续往左子树或右子树找
        // 只要左边或右边有一条路满足条件，就返回 true
        return dfs(node.left, targetSum, currentSum) || dfs(node.right, targetSum, currentSum);
    }
}
