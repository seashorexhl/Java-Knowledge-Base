package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.BinaryTree;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-04-22 15:23
 * @Description:  二叉树 的中序遍历
 */
public class inorderTraversal {
    List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        inorderTraversal pt = new inorderTraversal();
        System.out.println(pt.inorder(root));
    }

    /**
     *  中序遍历 二叉树
     * */
    public List<Integer> inorder(TreeNode root) {
        Traversal(root);
        return res;
    }
    /**
     *  中序遍历
     * */
    public void Traversal(TreeNode root) {
        if (root == null) {
            return ;
        }
        Traversal(root.left);
        res.add(root.val);
        Traversal(root.right);
    }

}
