package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree;


/**
 * @Author: xhl
 * @Date: 2026-06-03 18:29
 * @Description: 树的节点的构建
 */
public final class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

