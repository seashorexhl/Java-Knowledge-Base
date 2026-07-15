package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

/**
 * @Author: xhl
 * @Date: 2026-06-22 12:12
 * @Description: 222 完全 二 叉 树的 节点数
 *  给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（从第 0 层开始），
 * 则该层包含 1~ 2h 个节点。
 */
public class countTreeNodes {

    static void main() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        countTreeNodes ctn = new countTreeNodes();

        int nodes = ctn.countNodes(root);

        System.out.println(nodes);
    }
    /**
     *  二分查找+位运算
     * */
    public int countNodes(TreeNode root) {
        // 判空
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

}
