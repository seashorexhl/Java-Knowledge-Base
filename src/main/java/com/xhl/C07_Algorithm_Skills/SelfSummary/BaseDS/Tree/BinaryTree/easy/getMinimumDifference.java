package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.BST.BSTreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-22 12:31
 * @Description: 530. 二叉搜索树的最小绝对差
 *
 */
public class getMinimumDifference {
    static void main() {
        BSTreeNode root = new BSTreeNode(4);
        root.left = new BSTreeNode(2);
        root.right = new BSTreeNode(6);
        root.left.left = new BSTreeNode(1);
        root.left.right = new BSTreeNode(3);

        getMinimumDifference gmd = new getMinimumDifference();
        int minimumDifference = gmd.getMinimumDifference(root);
        System.out.println("Minimum Difference: " + minimumDifference);
    }

    public int getMinimumDifference(BSTreeNode root) {


        return 0;
    }
}
