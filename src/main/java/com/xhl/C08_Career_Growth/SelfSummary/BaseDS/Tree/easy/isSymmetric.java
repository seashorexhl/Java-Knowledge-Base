package com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.easy;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-22 10:14
 * @Description: 101 对称二叉树
 */
public class isSymmetric {
    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        isSymmetric is = new isSymmetric();
        System.out.println(is.isSymmetric(root));

    }
    /**
     * 方法一：递归
    */
    public boolean isSymmetric(TreeNode root) {
        return root == null || checkMirror(root.left, root.right);
    }
    public boolean checkMirror(TreeNode left,TreeNode right){
        return (left == null && right == null) ||
                (left != null && right != null && left.val == right.val && checkMirror(left.left, right.right) && checkMirror(left.right, right.left));
    }
    /**
     * 方法二：迭代
     **/
    public boolean isSymmetric1(TreeNode root) {
        // 引入一个队列，这是把递归程序改写成迭代程序的常用方法。初始化时我们把根节点入队两次。
        // 每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像）
        // 然后将两个结点的左右子结点按相反的顺序插入队列中。

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
