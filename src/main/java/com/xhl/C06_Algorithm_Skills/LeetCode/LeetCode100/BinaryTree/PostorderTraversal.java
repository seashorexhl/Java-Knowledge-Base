package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.BinaryTree;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-08 18:22
 * @Description: 二叉树 的  后序遍历
 */
public class PostorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        PostorderTraversal pt = new PostorderTraversal();
        List<Integer> integers = pt.postorderTraversal(root);
        System.out.println(integers);
    }
    // 后续 遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;


        res.addAll(postorderTraversal(root.left));
        res.addAll(postorderTraversal(root.right));
        res.add(root.val);
        return res;
    }
}
