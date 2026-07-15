package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;

/**
 * @Author: xhl
 * @Date: 2026-06-29 21:51
 * @Description: TreeNode 树的节点类
 *  定义如下 针对 buildTree
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){

    }
    TreeNode(int x) { val = x; }

    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}