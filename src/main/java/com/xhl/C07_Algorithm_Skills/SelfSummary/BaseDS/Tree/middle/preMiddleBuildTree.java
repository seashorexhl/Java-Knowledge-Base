package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-22 12:15
 * @Description: 105. 从前序与中序遍历序列构造二叉树 ⭐⭐⭐
 *  递归 迭代
 */
public class preMiddleBuildTree {
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        preMiddleBuildTree pmt = new preMiddleBuildTree();

        System.out.println("-------------递归的方式求解--------------");
        TreeNode node = pmt.buildTree(preorder, inorder);
        System.out.println("方法一：递归:");
//        pmt.printTree(node);
        System.out.println(pmt.serialize(node));

        System.out.println("-------------迭代的方式求解--------------");
        System.out.println("方法二：迭代:");
        TreeNode node1 = pmt.buildTree1(preorder, inorder);
        //        pmt.printTree(node1);
        System.out.println(pmt.serialize(node1));
    }
    /**
     *  方法一：递归
     * */
    private Map<Integer, Integer> indexMap;

    /**
     * 思路 ⭐⭐⭐
     * 找根节点：看前序/后序数组的边界（前序看左边界，后序看右边界）。
     * 定大小：只要有了中序遍历，或者有了前/后序中左子树的根节点，就能算出 leftSize（左子树节点个数）。
     * 切分数组：
     * 中序遍历永远是根据 inIndex 从中间劈开。
     * 前序/后序遍历永远是根据算出来的 leftSize 进行等长截取。
     * */
    // 辅助 函数
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
    /**
     * 递归方法
     * */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);

    }
    /**
     *  方法二：迭代 利用栈模拟递归
     * */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        // 边界条件
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        // 1. 创建根节点，并将其压入栈中
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        // 2. 中序遍历的索引指针，初始指向第一个元素
        int inorderIndex = 0;

        // 3. 从 preorder 的第二个元素开始遍历
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];

            // 获取栈顶元素（即当前正在处理的父节点候选者）
            TreeNode node = stack.peek();

            // 情况 A：栈顶元素不等于当前中序元素
            // 说明我们还在向左走，当前 preorderVal 是 node 的左孩子
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            }
            // 情况 B：栈顶元素等于当前中序元素
            // 说明左子树已经处理完毕（或者为空），需要向右转
            else {
                // 循环弹出栈顶，并向右移动中序指针
                // 这一步是为了找到那个“拥有右孩子”的祖先节点
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }

                // 循环结束后，node 是最后一个被弹出的节点
                // 当前的 preorderVal 是 node 的右孩子
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
    /**
     * 方法三：哈希表优化和数组索引传递
     * */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        int m = preorder.length;
        int n = inorder.length;
        if(m != n){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>(m);
        for(int i = 0;i<n;i++){
            map.put(inorder[i],i);
        }
        return buildTree3(preorder,0,n-1,map,0,n-1);
    }

    public TreeNode buildTree3(int[] preorder,int preLeft,int preRight,
                              Map<Integer,Integer> map, int inLeft,int inRight ){
        if(preLeft >preRight || inLeft > inRight){
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int preIndex = map.get(rootVal);
        root.left = buildTree3(preorder,preLeft+1,preIndex - inLeft+preLeft,map,inLeft,preIndex-1);
        root.right = buildTree3(preorder,preIndex - inLeft + preLeft + 1,preRight,map,preIndex+1,inRight);
        return root;

    }
    /**
     * 打印树（横向打印，逆时针旋转90度）
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

    private void printTree(TreeNode node) {
        List<String> res = printTreeHelper(node, 0);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private List<String> printTreeHelper(TreeNode node, int level) {
        List<String> list = new ArrayList<>();
        if (node == null) {
            return list; // 返回空列表，避免上层 addAll 时抛出空指针异常
        }

        // 1. 先递归打印右子树（在控制台显示在上方）
        list.addAll(printTreeHelper(node.right, level + 1));

        // 2. 处理当前节点：根据层级生成缩进
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        list.add(sb.toString());

        // 3. 最后递归打印左子树（在控制台显示在下方）
        list.addAll(printTreeHelper(node.left, level + 1));

        return list;
    }
}