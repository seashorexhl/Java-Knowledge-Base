package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:03
 * @Description:  114. 二 叉树展开为链表
 */
public class flattenBSTree {
    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);

        flattenBSTree fb = new flattenBSTree();
        fb.flatten(root);
        printTree(root);
    }
    // 打印
    /**
     * 辅助方法：按层序遍历打印 二叉树 （用于验证结果）
     */
    public static void printTree(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }

    // 方法一：前序遍历
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preTraverse(root,list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i-1);
            TreeNode cur = list.get(i);
            prev.left = null;
            prev.right = cur;
        }
    }

    private void preTraverse(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preTraverse(root.left, list);
            preTraverse(root.right, list);
        }
    }
    // 方法二：前序遍历和展开同步进行
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }



}
