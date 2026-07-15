package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-01 01:05
 * @Description: 889 根据 二叉树的前序 和 后序遍历 构建 二叉树 constructFromPrePost
 * 注意：仅凭前序和后序遍历，是无法唯一确定一棵二叉树的！ 只有当二叉树是满二叉树或完全二叉树时，
 * 前序+后序才能唯一重构。如果某个节点只有一个子节点，前序和后序无法区分它是左孩子还是右孩子。
 */
public class prePostBuildTree {
    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
        prePostBuildTree ppb = new prePostBuildTree();
        TreeNode node = ppb.constructFromPrePost(preorder, postorder);
        List<Integer> list = ppb.levelTraverse(node);
        System.out.println(Arrays.toString(list.toArray()));

    }

    //    方法一：分治
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            postMap.put(postorder[i], i);
        }
        return dfs(preorder, postorder, postMap, 0, n - 1, 0, n - 1);
    }
    // 辅助
    public TreeNode dfs(int[] preorder, int[] postorder, Map<Integer, Integer> postMap, int preLeft, int preRight, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return null;
        }
        int leftCount = 0;
        if (preLeft < preRight) {
            leftCount = postMap.get(preorder[preLeft + 1]) - postLeft + 1;
        }
        return new TreeNode(preorder[preLeft],
                dfs(preorder, postorder, postMap, preLeft + 1, preLeft + leftCount, postLeft, postLeft + leftCount - 1),
                dfs(preorder, postorder, postMap, preLeft + leftCount + 1, preRight, postLeft + leftCount, postRight - 1));
    }
    /**
     * 打印 （层序遍历）
     * */
    public List<Integer> levelTraverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                res.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);

                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

        }
        return res;
    }
}
