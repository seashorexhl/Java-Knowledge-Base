package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.BST;

/**
 * @Author: xhl
 * @Date: 2026-06-05 10:58
 * @Description: 二 叉搜索树 的 节点构建
 */
public class BSTreeNode {

    public int val;
    public  BSTreeNode left;
    public  BSTreeNode right;

    public BSTreeNode() {}
    public BSTreeNode(int val) {
        this.val = val;
    }
    public BSTreeNode(int val,  BSTreeNode left,  BSTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
