package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-21 04:03
 * @Description: 二 叉树的锯齿形 层序遍历   ⭐⭐⭐
 *  BFS 层序遍历   广度优先遍历
 * 给你 二 叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，
 * 以此类推，层与层之间交替进行）。
 */
public class zigzagLevelOrder {
    static void main() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        zigzagLevelOrder zlo = new zigzagLevelOrder();
        System.out.println("二叉树的 锯齿形遍历：");
        System.out.println(zlo.zigzagLevelOrder(root));
        System.out.println("---------------------------");
        List<Integer> ans = zlo.zigzagLevelOrder1(root);
        System.out.println(ans);
    }

    /**
     * 广度优先遍历
     * BFS 层序遍历 + 双端队列（Deque）动态调整插入方向
     * 使用双端队列 (Deque) 代替普通列表
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 1. 初始化结果集，用于存储每一层的遍历结果
        List<List<Integer>> ans = new LinkedList<>();
        // 边界条件判断：如果根节点为空，直接返回空结果集
        if (root == null) {
            return ans;
        }
        // 2. 初始化队列，用于标准的 BFS（广度优先搜索）层序遍历
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        // 引入一个布尔变量 isOrderLeft 来记录当前层应该从左到右还是从右到左输出。每处理完一层，
        // 就将其取反 (isOrderLeft = !isOrderLeft)，从而实现“之”字形交替。
        // 3. 核心标志位：记录当前层的输出方向
        // true 表示从左到右，false 表示从右到左。每处理完一层就取反，实现“之”字形交替。
        boolean isOrderLeft = true;
        // 4. 只要队列不为空，就继续按层处理节点
        while (!nodeQueue.isEmpty()) {
            // 使用双端队列（Deque）来存储当前层的节点值
            // 它的核心优势是支持在队头和队尾进行 O(1) 的插入操作，完美契合之字形输出需求
            Deque<Integer> levelList = new LinkedList<>();
            // 记录当前层的节点数量（必须在 for 循环外记录，因为循环中队列大小会动态变化）
            int size = nodeQueue.size();
            // 5. 遍历当前层的所有节点
            for (int i = 0; i < size; ++i) {
                // 从队列头部取出当前节点
                TreeNode curNode = nodeQueue.poll();
                // 【核心逻辑】根据方向标志位，决定当前节点的值插入到双端队列的哪个位置
                if (isOrderLeft) {
                    // 从左到右输出：将值追加到双端队列的【尾部】
                    levelList.offerLast(curNode.val);
                } else {
                    // 从右到左输出：将值插入到双端队列的【头部】（利用头插法实现逆序）
                    levelList.offerFirst(curNode.val);
                }
                // 将当前节点的左右子节点加入队列，为下一层的遍历做准备
                // 注意：子节点的入队顺序始终是“先左后右”，不受当前层输出方向的影响
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            // 6. 将当前层的结果（双端队列）转换为普通链表并加入最终结果集
            ans.add(new LinkedList<>(levelList));
            // 7. 翻转方向标志位，准备处理下一层
            isOrderLeft = !isOrderLeft;
        }
        // 返回“之”字形层序遍历的结果
        return ans;
    }

    public List<Integer> zigzagLevelOrder1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null ){
            return ans;
        }
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while(!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            Deque<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft){
                    levelList.offerLast(curNode.val);
                }else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null){
                    nodeQueue.offer(curNode.left);
                }
                if(curNode.right != null){
                    nodeQueue.offer(curNode.right);
                }
            }
            for (Integer val : levelList) {
                ans.add(val);
            }

            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }
}