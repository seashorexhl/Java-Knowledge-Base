package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.easy;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-22 09:17
 * @Description: 226 翻转 二叉树
 * 给你一棵 二 叉树的根节点 root ，翻转这棵 二 叉树，并返回其根节点。
 */
public class invertTree {
    static void main() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        invertTree it =  new invertTree();
        TreeNode node = it.invertTree(root);

        it.preorderRecursive(node);

    }
    // 翻转
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
    //
    public void preorderRecursive(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");      // 访问根节点
        preorderRecursive(root.left);          // 遍历左子树
        preorderRecursive(root.right);         // 遍历右子树
    }
}