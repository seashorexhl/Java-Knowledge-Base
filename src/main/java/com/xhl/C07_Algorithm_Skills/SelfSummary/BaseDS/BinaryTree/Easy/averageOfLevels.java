package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.Easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-22 12:29
 * @Description: 二 叉树的 层平均值
 */
public class averageOfLevels {
    static void main() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        averageOfLevels aoL= new averageOfLevels();
        List<Double> doubles = aoL.averageOfLevels(root);
        System.out.println("二叉树的 层 平均值 深度优先搜索:"+ doubles);

        averageOfLevels aoL2 = new averageOfLevels();
        List<Double> doubles2 = aoL2.BSaverageOfLevels(root);
        System.out.println("二叉树广度优先搜索 方法计算平均值:"+ doubles2);
    }
    /**
     *  深度优先
     * */
    public List<Double> averageOfLevels(TreeNode root) {

        return null;
    }
    /**
     *  广度优先
     * */
    public List<Double> BSaverageOfLevels(TreeNode root) {

        return null;
    }
}
