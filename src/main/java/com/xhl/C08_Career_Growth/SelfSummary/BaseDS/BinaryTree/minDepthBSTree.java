package com.xhl.Career_Growth.SelfSummary.BaseDS.BinaryTree;


import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-05 00:26
 * @Description: 二叉树 的最小深度
 */
public class minDepthBSTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(9);

        minDepthBSTree mdbstree = new minDepthBSTree();
        System.out.println(mdbstree.minDepth(root));
    }
    /*采用 BFS 框架 */
     public int minDepth(TreeNode root) {

        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //root 本身就是一层，depth 初始化为1
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) return depth;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            depth++;
        }
        return depth;
    }
}
