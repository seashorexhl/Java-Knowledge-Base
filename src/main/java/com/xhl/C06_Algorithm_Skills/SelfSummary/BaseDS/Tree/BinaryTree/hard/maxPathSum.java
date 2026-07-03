package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.hard;

import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-30 16:46
 * @Description: 124. 二叉树中的最大路径和
 */
public class maxPathSum {
    static void main() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);


    }

    public int maxPathSum(TreeNode root) {

        return 0;
    }
    /**
     *  DFS 遍历
     * */
    //判断树中是否存在一条从根节点到叶子节点的路径，使得路径上所有节点的值加起来等于 target
    public List<Integer> Traverse(TreeNode root, int target) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return null;
        }
//        int sum = root.val;
//        int leftSum = Traverse(root.left,target);
//        int rightSum = Traverse(root.right,target);


        return  res;
    }


}
