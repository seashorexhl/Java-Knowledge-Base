package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-04 01:13
 * @Description: 104 二叉树 的 最大深度
 *  方法一：用 traverse函数 遍历，维护depth变量。
 *  方法二：递归实现 左右子树的最大深度
 */
public class MaxDepthSolution  {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("后序遍历：");
        MaxDepthSolution mds= new MaxDepthSolution();
        int result = mds.maxDepth(root);
        System.out.println("二叉树的最大深度为: " + result);

        System.out.println("方法一：深度优先搜索：");
        int res = mds.maxDepth0(root);
        System.out.println(res);
        System.out.println("方法二：深度优先搜索：");

    }
        // 记录最大深度
        int res = 0;
        int maxDepth = 0;
        // 主函数
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            traverse(root);
            return res;
        }

        // 内部 DFS深度优先 遍历
        public void traverse(TreeNode root) {
            if (root == null){
                // 到达叶子节点
                res =Math.max(res,maxDepth);
                return;
            }

            //前序遍历位置
//            System.out.println("中序遍历：");
//            System.out.print(root.val+" ");
            maxDepth++;
            traverse(root.left);
//            System.out.println("前序遍历：");
//            System.out.print(root.val+" ");

            traverse(root.right);
            //后序 遍历位置
            maxDepth--;
            System.out.print(root.val+" ");
        }
    /**
     *  方法一：深度优先搜索 ⭐⭐⭐
     * */
    public int maxDepth0(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth0(root.left);
            int rightHeight = maxDepth0(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     *  方法二:最大深度
     * */
    int MaxDepth1(TreeNode root){
          if (root == null) return 0;
          int left = MaxDepth1(root.left);
          int right = MaxDepth1(root.right);
          int res = Math.max(left,right) + 1;
          return  res;
    }
    /**
     * 方法三、三元表达式 DFS递归简洁版
     * */
    public int maxDepth2(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth2(root.left), maxDepth2(root.right)) + 1;
    }
    /**
     *  方法四、广度优先搜索 BFS
     * */
    public int maxDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

}
