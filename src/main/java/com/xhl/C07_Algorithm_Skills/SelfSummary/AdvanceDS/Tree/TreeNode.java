package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Tree;

/**
 * @Author: xhl
 * @Date: 2026-06-11 03:07
 * @Description: 树的节点 的构建
 */
public class TreeNode {
    public int val;
    public TreeNode left,right;

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
