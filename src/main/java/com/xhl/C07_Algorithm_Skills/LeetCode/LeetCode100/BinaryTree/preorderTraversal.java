package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.BinaryTree;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-08 17:38
 * @Description: 二叉树 的 前序遍历
 */
public class preorderTraversal {
    // 二叉树 的 前序遍历
    List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        preorderTraversal pt = new preorderTraversal();
        System.out.println(pt.preorder(root));
    }

    public List<Integer> preorder(TreeNode root) {
        Traversal(root);
        return  res;

    }
    /**
     *  前序 遍历 二叉树
     * */
    void Traversal(TreeNode root){
        if (root == null) return;

        //前序遍历位置
        res.add(root.val);
        Traversal(root.left);
        Traversal(root.right);
    }
}
