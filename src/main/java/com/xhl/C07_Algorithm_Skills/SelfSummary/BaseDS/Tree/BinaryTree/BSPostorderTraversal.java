package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.BST.BSTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-06 00:06
 * @Description: 二叉树 的 后序遍历
 *
 */
public class BSPostorderTraversal {
    public static void main(String[] args) {
        BSTreeNode root = new BSTreeNode(1);
        root.left = new BSTreeNode(2);
        root.right = new BSTreeNode(3);
        root.left.left = new BSTreeNode(4);
        root.left.right = new BSTreeNode(5);

        BSPostorderTraversal bsp= new BSPostorderTraversal();
        List<Integer> list = bsp.postorderTraversal(root);
        System.out.println("正常后序遍历");
        System.out.println(list.toString());
        System.out.println("优化后的后序遍历：");
        BSPostorderTraversal  bsp2= new BSPostorderTraversal();
        List<Integer> list2 = bsp2.optimizePostorderTraversal(root);
        System.out.println(list2.toString());
    }
    /**
     *  递归 后序遍历
     * */
    public List<Integer> postorderTraversal(BSTreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.val);
        return list;
    }
    /**
     *  优化 后序遍历
     * */
    // 公开的入口方法
    public List<Integer> optimizePostorderTraversal(BSTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 将结果集传入辅助递归函数
        postorderHelper(root, result);
        return result;
    }

    /**
     *  辅助递归函数：严格遵循 左 -> 右 -> 根 的顺序
     * */
    private void postorderHelper(BSTreeNode node, List<Integer> list) {
        if (node == null) {
            return; // 递归终止条件
        }
        postorderHelper(node.left, list);  // 1. 先递归遍历左子树
        postorderHelper(node.right, list); // 2. 再递归遍历右子树
        list.add(node.val);                // 3. 最后访问当前根节点
    }

}
