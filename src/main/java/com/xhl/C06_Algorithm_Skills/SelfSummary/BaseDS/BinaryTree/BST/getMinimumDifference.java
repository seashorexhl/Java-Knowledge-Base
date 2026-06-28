package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.BST;


/**
 * @Author: xhl
 * @Date: 2026-06-22 13:43
 * @Description: 530. 二 叉搜索树的最小绝对差
 * 给你一个 二 叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 */
public class getMinimumDifference {
    //    方法一：中序遍历
    Integer pre;
    Integer ans;

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
        ans = Integer.MAX_VALUE;
        pre = null;
        dfs(root);
        return ans;
    }
   //  递归 方法实现
    public void dfs(BSTreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == null) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }

}
