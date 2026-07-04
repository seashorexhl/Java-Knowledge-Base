package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-04 20:42
 * @Description: 数组 转 二叉树  ArrayToTreeDemo 构造二叉树
 */
public class TreeBuilder {

    public static void main(String[] args) {
        Integer[] nums = {5,4,8,11,null,13,4,7,2,null,null,null,1};
        // 你的测试数据：5,4,8,11,null,13,4,7,2,null,null,null,1
        Integer[] data = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};

        // 构建树
        TreeNode root = buildTree(data);

        // 打印验证
        System.out.print("构建的二叉树层序遍历结果为: ");
        printTree(root);
        // 预期输出: 5 4 8 11 13 4 7 2 1
    }
    /**
     * 核心方法：将层序遍历的 数组转换为 二叉树
     */
    public static TreeNode buildTree(Integer[] nums) {
        // 1. 边界防御：处理空数组或首元素为 null 的情况
        if (nums == null || nums.length == 0 || nums[0] == null) {
            return null;
        }

        // 2. 创建根节点并入队
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1; // 指向当前正在处理的数组元素下标

        // 3. 只要队列不为空，就不断弹出节点并为其挂载左右孩子
        while (!queue.isEmpty() && i < nums.length) {
            TreeNode node = queue.poll();// 弹出

            // 处理左孩子
            if (i < nums.length && nums[i] != null) {
                node.left = new TreeNode(nums[i]);
                queue.add(node.left); // 只有非空节点才入队
            }
            i++; // 无论是否为 null，数组指针都要前进

            // 处理右孩子
            if (i < nums.length && nums[i] != null) {
                node.right = new TreeNode(nums[i]);
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }
    /**
     * 辅助方法：按层序遍历打印 二叉树 （用于验证结果）
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

    //方法三：寻找前驱节点
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

}
