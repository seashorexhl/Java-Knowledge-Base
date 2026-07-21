package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:00
 * @Description: 106. 从中序与后序遍历序列构造二叉树
 *
 */
public class midPostBuildTree {


    static void main() {
        int[]  inorder = {9,3,15,20,7};
        int[] postorder  = {9,15,7,20,3};

        midPostBuildTree mtp = new midPostBuildTree();

        System.out.println("--------------方法一：递归-------------------");
        TreeNode node = mtp.buildTree(inorder,postorder );
        mtp.printTree(node);
        System.out.println(mtp.serialize(node));
        System.out.println("--------------方法二：迭代-------------------");
        TreeNode node1 = mtp.buildTree1(inorder,postorder );
        mtp.printTree(node1);
        System.out.println(mtp.serialize(node1));

    }
    /**
     * 方法一：递归
     */
    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<>();
    public TreeNode helper(int in_left, int in_right) {
        // 如果这里没有节点构造 二 叉 树了，就结束
        if (in_left > in_right) {
            return null;
        }

        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两棵子树
        int index = idx_map.get(root_val);

        // 下标减一
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    /**
     * 方法二：迭代
     */
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }
    /**
     * 方法三：哈希表优化和数组索引传递
     * */
    public TreeNode buildTree3(int[] inorder, int[] postorder) {
        int m = inorder.length;
        int n = postorder.length;
        if (m != n) {
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>(m);
        for (int i = 0; i < m; i++) {
            map.put(inorder[i], i);
        }

        return buildTree3(inorder,0,n-1,map,0,n-1);
    }

    private TreeNode buildTree3(int[] inorder, int inLeft, int inRight, Map<Integer, Integer> map, int postLeft, int postRight) {

        return null;
    }

    /**
     * 打印树（前序打印）
     * 返回一个包含树形结构的字符串列表，最后统一打印
     */
    public String serialize(TreeNode root) {
        if (root == null) return "[]";

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(String.valueOf(node.val));
                // 即使是叶子节点，也要把它的左右孩子（null）入队，保证结构完整
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add("null");
            }
        }

        // 去掉末尾多余的 "null"，让输出更整洁
        while (result.size() > 0 && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        return "[" + String.join(",", result) + "]";
    }
    /**
     * 打印 方法
     * */
    private void printTree(TreeNode node) {
        List<String> res = printTreeHelper(node, 0);
        System.out.println(Arrays.toString(res.toArray()));
    }
    /**
     *  打印辅助 方法
     * */
    private List<String> printTreeHelper(TreeNode node, int level) {
        List<String> list = new ArrayList<>();
        if (node == null) {
            return list; // 返回空列表，避免上层 addAll 时抛出空指针异常
        }
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        // 根 -> 左 -> 右
        list.add(sb.toString());
        list.addAll(printTreeHelper(node.left, level + 1));
        list.addAll(printTreeHelper(node.right, level + 1));
        return list;
    }
}