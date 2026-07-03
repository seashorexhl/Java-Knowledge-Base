package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.middle;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-21 02:37
 * @Description: 二 叉 树的层序遍历
 */
public class levelOrder {
    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        levelOrder lo = new levelOrder();
        System.out.println("二叉树的层序遍历：");
        System.out.println(lo.levelOrder(root));
    }
    // 二 叉树的层序遍历   BFS 算法
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(list);
        }
        return result;
    }
}
