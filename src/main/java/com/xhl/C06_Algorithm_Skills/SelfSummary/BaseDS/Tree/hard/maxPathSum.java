package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.hard;

import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-30 16:46
 * @Description: 124. 二叉树中的最大路径和
 */
public class maxPathSum {
    // 方法一：递归
    int maxSum = Integer.MIN_VALUE;

    static void main() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        maxPathSum mps = new maxPathSum();
        int sum = mps.maxPathSum(root);
        System.out.println(sum);
    }

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);

    }
}
