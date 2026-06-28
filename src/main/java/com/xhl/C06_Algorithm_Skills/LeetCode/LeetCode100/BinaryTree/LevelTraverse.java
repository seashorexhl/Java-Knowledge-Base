package com.xhl.Algorithm_Skills.LeetCode.LeetCode100.BinaryTree;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-08 18:28
 * @Description: 层序遍历二叉树 BFS
 */
public class LevelTraverse {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        LevelTraverse lT = new LevelTraverse();
        lT.LevelTraverse(root);

    }
    // 分层遍历
    public  void   LevelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                System.out.print(cur.val +" ");
                if (cur.left != null){
                    queue.offer(cur.left);
                }

                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
    }
}
