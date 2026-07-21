package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.BST.BSTreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-22 12:31
 * @Description: 530. 二叉搜索树的最小绝对差
 *  DFS
 */
public class getMinimumDifference {
    int pre;
    int ans;

    static void main() {
        BSTreeNode root = new BSTreeNode(422);
        root.left = new BSTreeNode(231);
        root.right = new BSTreeNode(642);
        root.left.left = new BSTreeNode(142);
        root.left.right = new BSTreeNode(362);

        getMinimumDifference gmd = new getMinimumDifference();
        int minimumDifference = gmd.getMinimumDifference(root);
        System.out.println("Minimum Difference: " + minimumDifference);
    }

    public int getMinimumDifference(BSTreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }
    public void dfs(BSTreeNode root){
        if(root == null)
            return ;

        dfs(root.left);
        if(pre ==-1){
            pre = root.val;
        }else{
            ans = Math.min(ans,root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
