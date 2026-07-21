package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree;

/**
 * @Author: xhl
 * @Date: 2026-07-20 16:54
 * @Description: TreeNode  树的节点
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
