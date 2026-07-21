package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.middle;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle.BinarySTIterator;
import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:39
 * @Description: 173.二 叉搜索树 迭代器
 *
 */
public class BSTIterator {
    /**
     *  方法二：迭代
     * */
    private TreeNode cur;
    private Deque<TreeNode> stack;
    public  BSTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }

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

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        return ret;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}
