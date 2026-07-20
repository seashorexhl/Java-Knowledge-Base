package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-04 18:40
 * @Description: 101 判断 二叉树 是否是 对称
 *
 */
public class isSymmetricSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        isSymmetricSolution is= new isSymmetricSolution();
        System.out.println(is.isSymmetric(root));
    }
    /**
     * 方法一:递归
     * */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return checkSymmetric(root.left,root.right);
    }
    //递归方法求解
    public boolean checkSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return  (p.val == q.val) && checkSymmetric(p.left,q.right) && checkSymmetric(p.right,q.left);
    }
    /**
     * 方法二:迭代方法求解
     * */
    public boolean isSymmetric1(TreeNode root) {
            return check(root, root);
    }

    public boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
}
