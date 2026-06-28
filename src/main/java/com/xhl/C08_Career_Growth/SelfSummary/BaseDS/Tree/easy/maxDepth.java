package com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.easy;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-22 08:06
 * @Description: 104 二 叉树的  最大深度
 */
public class maxDepth {
    static void main() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(15);
        root.left.right = new TreeNode(7);

        maxDepth md = new maxDepth();
        System.out.println("二 叉树的  最大深度 为：" + md.maxDepth(root));

        System.out.println("方法一： 深度优先搜索：" + md.maxDepthDFS(root));
        System.out.println("方法二： 广度优先搜索：" + md.maxDepthBFS(root));
    }
    // 简易办法
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //    方法一：深度优先搜索  递归计算出其左子树和右子树的最大深度 +1;
    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepthDFS(root.left);
            int right = maxDepthDFS(root.right);
            return Math.max(left, right) + 1;
        }
    }

    //    方法二：广度优先搜索
    public int maxDepthBFS(TreeNode root) {
        int res = 0;
        // 1.边界条件
        if(root == null) return res;
        // 2.创建 队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 3.循环 计数
        while(!queue.isEmpty()){
            int size = queue.size();
            while (size>0){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                size--;
            }
            res++;
        }
        return res;
    }
}
