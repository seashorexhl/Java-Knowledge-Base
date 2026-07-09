package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.easy;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-05 07:16
 * @Description: 104 二叉树 的 最大深度
 *  通过分解问题得到答案
 */
public class BSMaxDepth {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        BSMaxDepth BSMaxDepth = new BSMaxDepth();
        int i = BSMaxDepth.MaxDepth(root);
        System.out.println("Max Depth: " + i);
    }

    // 输入 根节点 计算返回这棵二叉树的最大深度
    int MaxDepth(TreeNode root) {
        if (root == null) return 0;
        // 递归计算左右子树最大深度
        return Math.max(MaxDepth(root.left), MaxDepth(root.right)) + 1;
    }
    //
}
