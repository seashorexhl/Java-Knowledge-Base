package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-04 20:39
 * @Description:  路径总和
 */
public class hasPathSumSolution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        hasPathSumSolution hps = new hasPathSumSolution();
        int sum =5;
        System.out.println("是否存在和为"+sum+"的路径？"+hps.hasPathSum(root,sum));

        int sum2 =4;
        System.out.println("是否存在和为"+sum+"的路径？"+hps.hasPathSumRe(root,sum2));
    }
    // 方法一：广度优先搜索
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();

        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }
    // 方法二、递归
    public boolean hasPathSumRe(TreeNode root, int sum){
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null){
            return sum == root.val;
        }
        return hasPathSumRe(root.left,sum- root.val) || hasPathSumRe(root.right,sum- root.val);
    }
}
