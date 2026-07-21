package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:39
 * @Description: 173. 二叉搜索树迭代器
 *  方法一:扁平化
 *  方法二:迭代
 */
public class BSTIterator {

    /**
     * 方法一：扁平化
     * */
    private int idx;
    private List<Integer> arr;
    public BSTIterator(TreeNode root) {
        idx = 0;
        arr = new ArrayList<Integer>();
        inorderTraversal(root, arr);
    }

    static void main() {
        TreeNode root = new TreeNode(1);

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
