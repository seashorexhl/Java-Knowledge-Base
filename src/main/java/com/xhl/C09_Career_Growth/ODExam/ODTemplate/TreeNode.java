package com.xhl.C09_Career_Growth.ODExam.ODTemplate;

/**
 * @Author: xhl
 * @Date: 2026-06-30 23:00
 * @Description: 树节点构建
 */
public class TreeNode {
    public int val;
    public TreeNode left,right;

    public TreeNode() {}
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val,TreeNode left,TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
