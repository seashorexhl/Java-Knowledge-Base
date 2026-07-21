package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:03
 * @Description:  114. 二 叉树展开为链表 ⭐⭐⭐⭐
 *  方法一：前序遍历
 *  方法二：前序遍历和展开同步进行
 *  方法三：层序遍历
 */
public class flattenBSTree {
    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);

        flattenBSTree fb = new flattenBSTree();
        fb.flatten(root);
        printTree(root);
    }
    /**
     * 辅助方法：按BFS层序遍历打印 二叉树 （用于验证结果）
     */
    public static void printTree(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }

    /**
     * 方法一：前序遍历
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preTraverse(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode cur = list.get(i);
            prev.left = null;
            prev.right = cur;
        }
    }
    /**
     * 前序遍历
     * */
    private void preTraverse(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preTraverse(root.left, list);
            preTraverse(root.right, list);
        }
    }

    /**
     * 方法二：前序遍历和展开同步进行
     */
    public void flatten1(TreeNode root) {

        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    /**
     * 方法三：寻找前驱节点
     * 将二叉树原地展开为单链表,左子树的最右节点，恰好是右子树的前驱节点.
     * 有左子树，找最右；原右树，接最右；左变空，左挪右；往右走，继续找。
     */
    public void flatten3(TreeNode root) {
        TreeNode curr = root;
        // 检查当前节点 curr 有没有左子树
        while (curr != null) {
            // 【动作1：备份】先把左子树存起来，因为等下要修改指针，怕弄丢了
            if (curr.left != null) {
                // 【动作2：找位置】去左子树里，找到“最右下角”的那个节点（也就是左子树的最末端）
                TreeNode next = curr.left;
                // 左子树中 找到最右边的节点
                TreeNode pre = next;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 【动作3：接尾巴】把 curr 原本的右子树，挂到左子树的最右下角上
                pre.right = curr.right;
                // 【动作4：断左链】左子树已经处理完了，把原来的左指针清空（符合题目要求）
                curr.left = null;
                // 【动作5：移主干】把刚刚备份的左子树（next），挪到 curr 的右边来
                curr.right = next;
            }
            // 【动作6：往下走】当前节点的左边已经被拍平到右边了，
            curr = curr.right;
        }
    }
}
