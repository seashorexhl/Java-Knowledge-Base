package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:39
 * @Description: 173. 二叉搜索树迭代器
 *  方法一:扁平化
 *  方法二:迭代 见 BinarySTIterator
 */
public class BSTIterator {



    static void main() {
        // 1. 构建一棵简单的二叉搜索树用于测试
        //       7
        //      / \
        //     3   15
        //        /  \
        //       9    20
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        // 2. 初始化迭代器
        BinarySTIterator iterator = new BinarySTIterator(root);

        // 3. 测试 next() 和 hasNext()
        System.out.println("当前是否有下一个元素: " + iterator.hasNext()); // true
        System.out.println("next: " + iterator.next()); // 3
        System.out.println("next: " + iterator.next()); // 7
        System.out.println("next: " + iterator.next()); // 9

        // 4. 循环调用直到遍历结束
        while (iterator.hasNext()) {
            System.out.println("next: " + iterator.next()); // 15, 20
        }

        System.out.println("当前是否有下一个元素: " + iterator.hasNext()); // false
    }

    /**
     * 方法一：扁平化
     * */

    private int idx;
    private List<Integer> arr;
    public BSTIterator(TreeNode root) {
        idx = 0;
        arr = new ArrayList<>();
        inorderTraversal(root, arr);
    }
    public int next() {
        return arr.get(idx++);
    }

    public boolean hasNext() {
        return idx < arr.size();
    }

    private void inorderTraversal(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, arr);
        arr.add(root.val);
        inorderTraversal(root.right, arr);
    }

}
