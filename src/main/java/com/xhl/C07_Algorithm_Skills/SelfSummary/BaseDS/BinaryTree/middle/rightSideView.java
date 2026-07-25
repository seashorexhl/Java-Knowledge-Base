package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.BST.BSTreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-20 16:13
 * @Description: 199 二 叉 树的 右视图
 *  深度优先搜索 DFS 广度优先搜索
 */
public class rightSideView {
    static void main() {
        BSTreeNode root = new BSTreeNode(1);
        root.left = new BSTreeNode(2);
        root.right = new BSTreeNode(3);
        root.left.left = new BSTreeNode(4);
        root.left.left.left = new BSTreeNode(5);

        rightSideView rs = new rightSideView();
        System.out.println("深度优先遍历：");
        System.out.println(rs.rightSideView(root));

        System.out.println("广度优先遍历：");
        System.out.println(rs.rightSideViewBFS(root));
    }
    /**
     * 方法一：深度优先搜索 DFS
     */
    public List<Integer> rightSideView(BSTreeNode root) {
        //  使用哈希表记录每一层深度对应的最右侧节点的值
        //  Key: 节点所在的深度, Value: 该深度下最右侧节点的值
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        //  记录遍历过程中遇到的最大深度，用于最后构建结果列表
        int max_depth = -1;

        // 构造节点和 深度 栈
        Deque<BSTreeNode> nodeStack = new LinkedList<>();
        Deque<Integer> depthStack = new LinkedList<>();
        // 将根节点及其深度（0）压入栈中，作为遍历的起点
        nodeStack.push(root);
        depthStack.push(0);
        // 当节点栈不为空时，持续进行深度优先遍历
        while (!nodeStack.isEmpty()) {
            // 弹出栈顶的节点及其对应的深度
            BSTreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            // 如果弹出的节点不为空，则进行处理
            if (node != null) {
                // 维护 二 叉 树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 【核心逻辑】：如果当前深度在哈希表中还不存在对应的值，则进行记录
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }
                // 【关键步骤】：先将左子节点压栈，再将右子节点压栈
                //  由于栈是“后进先出(LIFO)”的数据结构，右子节点会被先弹出处理
                //  这保证了我们在每一层深度时，总是优先访问最右侧的节点
                nodeStack.push(node.left);
                nodeStack.push(node.right);
                // 将左右子节点对应的深度（当前深度 + 1）依次压入深度栈
                // 注意：深度栈的压入顺序必须与节点栈完全对应
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }
        // 遍历结束后，根据最大深度构建最终的右视图结果列表
        List<Integer> rightView = new ArrayList<>();
        // 从第 0 层到 max_depth 层，依次取出对应深度的最右侧节点值
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }
        return rightView;
    }
    /**
     *  广度优先搜索 BFS 层序遍历
     * */
    public List<Integer> rightSideViewBFS(BSTreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<>();
        int max_depth = -1;
        // 队列
        Queue<BSTreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>();
        nodeQueue.add(root);
        depthQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            BSTreeNode node = nodeQueue.remove();
            int depth = depthQueue.remove();

            if (node != null) {
                // 维护 二 叉 树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可
                rightmostValueAtDepth.put(depth, node.val);

                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
                depthQueue.add(depth + 1);
                depthQueue.add(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

}
