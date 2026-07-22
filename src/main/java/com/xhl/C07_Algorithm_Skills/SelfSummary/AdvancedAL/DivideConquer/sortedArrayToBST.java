package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DivideConquer;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.Random;

/**
 * @Author: xhl
 * @Date: 2026-06-25 10:58
 * @Description: 108. 将有序数组转换为二叉搜索树
 *
 */
public class sortedArrayToBST {
    /**
     *  方法三：中序遍历，选择任意一个中间位置数字作为根节点
     * */

    Random rand = new Random();

    static void main() {
        int[] nums = {-10, -3, 0, 5, 9};
        sortedArrayToBST sab = new sortedArrayToBST();
        TreeNode node = sab.sortedArrayToBST(nums);

    }

    /**
     * 方法一：中序遍历，总是选择中间位置左边的数字作为根节点
     *
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return  helper(nums, 0, nums.length - 1);
    }

    /**
     *  辅助函数
     * */
    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     *  方法二：中序遍历，总是选择中间位置右边的数字作为根节点
     * */
    public TreeNode helper1(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper1(nums, left, mid - 1);
        root.right = helper1(nums, mid + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择任意一个中间位置数字作为根节点
        int mid = (left + right + rand.nextInt(2)) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper2(nums, left, mid - 1);
        root.right = helper2(nums, mid + 1, right);
        return root;
    }
}