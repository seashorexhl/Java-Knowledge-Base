package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.BST.BSTreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-05 23:19
 * @Description: 二 叉 搜索树 的前序遍历
 */
public class BSPreorderTraversal {
    public static void main(String[] args) {
        BSTreeNode root = new BSTreeNode(1);
        root.left = new BSTreeNode(2);
        root.left.left = new BSTreeNode(3);
        root.left.right = new BSTreeNode(4);
        root.right = new BSTreeNode(5);

        BSPreorderTraversal bsP = new BSPreorderTraversal();
        System.out.println(bsP.PreorderTraversal(root));

    }
    // 二 叉树的 前序遍历 返回 整数数组
    public List<Integer> PreorderTraversal(BSTreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        list.add(root.val);
        list.addAll(PreorderTraversal(root.left));
        list.addAll(PreorderTraversal(root.right));
        return list;
    }

}
