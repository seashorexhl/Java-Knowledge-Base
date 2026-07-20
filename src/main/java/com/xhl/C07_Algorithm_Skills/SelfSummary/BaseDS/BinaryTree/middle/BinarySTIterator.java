package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-07-20 17:21
 * @Description:  173. 二叉搜索树迭代器
 */
public class BinarySTIterator {
    /**
     *     方法二：迭代
     * */
    private TreeNode cur;
    private Deque<TreeNode> stack;
    public  BinarySTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }

    static void main(String[] args) {

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
