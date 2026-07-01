package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.BinaryTree;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-08 18:28
 * @Description: 层序遍历二叉树 BFS
 */
public class levelTraverse {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        levelTraverse lT = new levelTraverse();
        System.out.println("-----------------分层遍历------------------");
        lT.LevelTraverse(root);
        System.out.println("-----------层序遍历 以列表的形式输出-----------------");
        List<Integer> list = lT.levelTraverse1(root);
        System.out.println(list);
        System.out.println("-------------按层分组输出 用索引控制逗号。-------------------------");
        List<List<Integer>> lists = lT.levelTraverseByLevel(root);
        System.out.print("[");
        for (int i = 0; i < lists.size(); i++) {
            System.out.print(lists.get(i)); // 直接打印 List，自带 [] 和逗号
            if (i < lists.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("------------------使用 StringBuilder--------------------");
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lists.size(); i++) {
            sb.append(lists.get(i)); // List 的 toString() 默认就是 [1,2,3] 格式，无需 toArray()
            if (i < lists.size() - 1) {
                sb.append(", "); // 只有不是最后一个元素时才追加逗号
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
    // 分层遍历
    public  void   LevelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                System.out.print(cur.val +" ");
                if (cur.left != null){
                    queue.offer(cur.left);
                }

                if (cur.right != null){
                    queue.offer(cur.right);

                }
            }
            depth++;
        }
    }
    // 层序遍历 打印 按层输出
    public List<Integer> levelTraverse1(TreeNode root) {
        // 1. 空节点快速返回
        if (root == null) {
            return Collections.emptyList(); // 返回不可变空列表，比 new ArrayList 更轻量
        }

        List<Integer> res = new ArrayList<>();
        // 2. 使用 ArrayDeque 替代 LinkedList，性能更好
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        // 3. 移除了无用的 depth 变量
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                res.add(node.val);

                // 4. 合并左右子树的判空逻辑（可选，看个人代码风格）
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return res;
    }
    // 进阶  按层分组返回
    public List<List<Integer>> levelTraverseByLevel(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>(size); // 传入 size 避免扩容

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(currentLevel);
        }
        return res;
    }
}
