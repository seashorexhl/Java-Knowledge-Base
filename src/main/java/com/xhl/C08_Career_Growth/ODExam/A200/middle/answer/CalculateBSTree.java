package com.xhl.Career_Growth.ODExam.A200.middle.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-10 06:05
 * @Description: 02 二叉树 计算 可以处理的最大任务数
 * 根据中序和前序遍历构造 二叉树
 *  二 叉树的构造 + 二 叉树的后序遍历 +  二 叉树的中序遍历
 */

public class CalculateBSTree {

    // 方法：根据中序和前序遍历构造 二叉树
    // 参数：preorder 前序遍历的结果，inorder 中序遍历的结果
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 调用 辅助方法，传入遍历结果和对应的开始结束索引
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    // 辅助方法：根据中序和前序遍历的一部分构造子树
    private static TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // 如果前序遍历的开始索引大于结束索引，说明这部分遍历结果为空，返回null
        if (preStart > preEnd) return null;

        // 创建根节点，值为前序遍历的第一个元素
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // 初始化中序遍历中根节点的索引
        // 在中序遍历中找到根节点的位置
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }

        // 计算左子树的大小
        int leftTreeSize = inIndex - inStart;

        // 递归构造左子树和右子树
        root.left = build(preorder, preStart + 1, preStart + leftTreeSize, inorder, inStart, inIndex - 1);
        root.right = build(preorder, preStart + leftTreeSize + 1, preEnd, inorder, inIndex + 1, inEnd);

        // 返回构造好的根节点
        return root;
    }

    // 方法：更新节点值为其所有子节点的和
    // 参数：node 需要更新的节点
    private static int updateTree(TreeNode node) {
        // 如果节点为空，返回0
        if (node == null) return 0;
        // 递归更新左子树和右子树，并计算子树的和
        int leftSum = updateTree(node.left);
        int rightSum = updateTree(node.right);
        // 保存当前节点的值
        int oldVal = node.val;
        // 更新当前节点的值为子树的和
        node.val = leftSum + rightSum;
        // 返回当前子树的和（包括当前节点原来的值）
        return node.val + oldVal;
    }

    // 方法：中序遍历
    // 参数：node 需要遍历的节点，result 保存遍历结果的列表
    private static void inorderTraversal(TreeNode node, ArrayList<Integer> result) {
        // 如果节点为空，直接返回
        if (node == null) return;
        // 递归遍历左子树
        inorderTraversal(node.left, result);
        // 将当前节点的值添加到结果列表
        result.add(node.val);
        // 递归遍历右子树
        inorderTraversal(node.right, result);
    }

    // 主方法
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读取一行输入，分割成字符串数组，转换为整数数组，作为中序遍历的结果
        int[] inorder  = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 同样处理前序遍历的结果
        int[] preorder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 根据中序和前序遍历的结果构造二叉树
        TreeNode root = buildTree(preorder, inorder);
        // 更新二叉树的节点值
        updateTree(root);
        // 创建列表，保存中序遍历的结果
        ArrayList<Integer> result = new ArrayList<>();
        // 中序遍历二叉树，保存结果
        inorderTraversal(root, result);

        // 打印遍历结果
        result.forEach(value -> System.out.print(value + " "));
    }

    // 定义树的节点结构
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
}
