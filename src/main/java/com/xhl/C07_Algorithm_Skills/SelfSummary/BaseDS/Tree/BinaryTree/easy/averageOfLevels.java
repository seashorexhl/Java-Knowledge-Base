package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.BinaryTree.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-22 12:29
 * @Description: 637 二 叉树的 层平均值
 */
public class averageOfLevels {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        averageOfLevels aol = new averageOfLevels();
        List<Double> res = aol.averageOfLevels(root);
        System.out.println(Arrays.toString(res.toArray()));
    }
    //  方法一: 深度优先搜索
    public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Double> sums = new ArrayList<>();
        dfs(root,0,counts,sums);
        List<Double> averages = new ArrayList<>();
        int size = sums.size();
        for(int i=0;i<size;i++){
            averages.add(sums.get(i)/counts.get(i));
        }
        return averages;
    }
    // DFS 方法
    public void dfs(TreeNode root,int level,List<Integer> counts,List<Double> sums){
        if(root == null){
            return;
        }
        if(level<sums.size()){
            sums.set(level,sums.get(level)+root.val);
            counts.set(level,counts.get(level)+1);
        }else{
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left,level +1,counts,sums);
        dfs(root.right,level+1,counts,sums);
    }
    // 方法二：广度优先搜索
    public List<Double> averageOfLevels1(TreeNode root) {

        List<Double> averages = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                TreeNode left = node.left, right = node.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

}
