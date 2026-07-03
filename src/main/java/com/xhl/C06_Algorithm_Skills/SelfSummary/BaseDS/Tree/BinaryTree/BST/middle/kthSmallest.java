package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.BST.middle;



import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.BST.BSTreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-06-22 15:10
 * @Description: 230 二 叉搜索树中 第K 小的元素
 */
public class kthSmallest {
    static void main() {
        BSTreeNode root = new BSTreeNode(5);
        root.left = new BSTreeNode(3);
        root.right = new BSTreeNode(6);
        root.left.left = new BSTreeNode(2);
        root.left.right = new BSTreeNode(4);
        root.left.left.left = new BSTreeNode(1);

        int k = 3;

        kthSmallest ks = new kthSmallest();
        System.out.println(ks.kthSmallest(root,k));


    }
    // 方法一：中序遍历
    public int kthSmallest(BSTreeNode root, int k) {
        Deque<BSTreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k==0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }

}
