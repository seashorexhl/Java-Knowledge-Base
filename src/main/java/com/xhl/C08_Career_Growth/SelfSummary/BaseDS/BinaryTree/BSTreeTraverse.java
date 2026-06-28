package com.xhl.Career_Growth.SelfSummary.BaseDS.BinaryTree;


import com.xhl.Career_Growth.SelfSummary.BaseDS.BinaryTree.BST.BSTreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-05 10:53
 * @Description: 二叉树 的 中序遍历
 */
public class BSTreeTraverse {
    public static void main(String[] args) {
        BSTreeNode root = new BSTreeNode
                (5);
        root.left = new BSTreeNode
                (6);
        root.right = new BSTreeNode
                (7);
        root.left.left = new BSTreeNode
                (8);
        root.left.right = new BSTreeNode
                (9);
        BSTreeTraverse bstt = new BSTreeTraverse();
        List<Integer> inorderRecursive = bstt.inorderRecursive(root);
        System.out.println("递归方法 中序遍历：");
        System.out.println(inorderRecursive.toString());
        BSTreeTraverse bstt1 = new BSTreeTraverse();
        List<Integer> inorder = bstt1.inorder(root);
        System.out.println("迭代方法 中序遍历：");
        System.out.println(inorder.toString());
    }
    // 二 叉树的中序遍历 递归
    public List<Integer> inorderRecursive(BSTreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        list.addAll(inorderRecursive(root.left));
        list.add(root.val);
        list.addAll(inorderRecursive(root.right));
        return list;
    }
    // 二叉树的 中序遍历 迭代遍历
    public List<Integer> inorder(BSTreeNode root) {
        List<Integer> res = new LinkedList<>();
        // 双端队列 LinkedList 多线程环境下使用，都需要 synchronizedDeque
        // Deque<BSTreeNode> stk = new LinkedList<BSTreeNode>();
        // 使用 ArrayDeque  两者都不是线程安全的
        Deque<BSTreeNode> stk = new ArrayDeque<>();

        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
