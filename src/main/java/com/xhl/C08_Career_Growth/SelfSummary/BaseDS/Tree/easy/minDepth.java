package com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.easy;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-27 13:55
 * @Description: 111. 二 叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 */
public class minDepth {
    static void main() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        minDepth md = new minDepth();
        int i = md.minDepth(root);
        System.out.println("二 叉树的最小深度"+i);
    }
    /**
     * 方法一：深度优先搜索
    */

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth+1;
    }

    //第一次遇到的叶子节点（左右子节点都为空）所在的层数，就是最小深度。
    public int minDepth1(TreeNode root) {
        // 1. 边界条件处理：如果根节点为空，说明树不存在，最小深度为 0
        if (root == null) {
            return 0;
        }
        // 2. 初始化一个队列，用于存放待访问的 QueueNode（包含节点和深度信息）

        Queue<QueueNode> queue = new LinkedList<>();
        // 将根节点入队，其初始深度为 1

        queue.offer(new QueueNode(root, 1));
        // 3. 开始层序遍历，只要队列不为空就继续
        while (!queue.isEmpty()) {
            // 3.1 从队首弹出一个节点及其深度
            QueueNode nodeDepth = queue.poll();

            TreeNode node = nodeDepth.node;
            // 3.2 判断是否为叶子节点（左右子节点都为空）
            // 因为 BFS 是逐层遍历的，所以第一次遇到的叶子节点必定在最小深度处
            int depth = nodeDepth.depth;
            if (node.left == null && node.right == null) {
                return depth;// 找到最小深度，直接返回
            }
            // 3.3 如果左子节点不为空，将左子节点入队，深度 +1
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            // 3.4 如果右子节点不为空，将右子节点入队，深度 +1
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }
        // 4. 兜底返回（理论上如果 root 不为空，循环内一定会 return，这行代码是为了满足编译器的返回值要求）
        return 0;
    }

    /**
     * 方法二：广度优先搜索
    */
    // 自定义 QueueNode
    class QueueNode {
        TreeNode node; // 当前树节点
        int depth;  // 该节点所在的深度（根节点深度为1）

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

}
