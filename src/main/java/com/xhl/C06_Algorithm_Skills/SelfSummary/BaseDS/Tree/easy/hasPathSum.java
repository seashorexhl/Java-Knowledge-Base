package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.easy;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-22 11:36
 * @Description: 112 路径总和
 */
public class hasPathSum {
    static void main() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        int targetSum = 22;


        hasPathSum hp = new hasPathSum();
        System.out.println(hp.hasPathSum(root, targetSum));

    }
    //
    public boolean hasPathSum(TreeNode root, int targetSum) {


        return  false;
    }
}
