package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:40
 * @Description: 236. 二 叉树的最近公共祖先 ⭐⭐⭐⭐⭐
 *  递归法 + 存储 父节点
 */
public class lowestCommonAncestor {
    /**
     * 方法二：存储父节点
     * */
    Map<Integer, TreeNode> parent = new HashMap<>();

    /**
     *  方法一：递归
     * */
    private TreeNode ans;

    public void Solution() {
        this.ans = null;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    Set<Integer> visited = new HashSet<>();

    /**
     *  主函数
     * */
    static void main() {
        // 1. 构建测试用的二叉树
        //         3
        //        / \
        //       5   1
        //      / \ / \
        //     6  2 0  8
        //       / \
        //      7   4
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // 实例化当前类
        lowestCommonAncestor lca = new lowestCommonAncestor();

        // 2. 测试方法一：递归法
        System.out.println("=== 测试方法一：递归法 ===");
        // 测试用例1：p=5, q=1，LCA应为 3
        TreeNode p1 = root.left;
        TreeNode q1 = root.right;
        TreeNode res1 = lca.lowestCommonAncestor(root, p1, q1);
        System.out.println("节点 5 和 1 的最近公共祖先: " + (res1 != null ? res1.val : "null")); // 预期输出: 3

        // 测试用例2：p=6, q=4，LCA应为 5
        TreeNode p2 = root.left.left;
        TreeNode q2 = root.left.right.right;
        TreeNode res2 = lca.lowestCommonAncestor(root, p2, q2);
        System.out.println("节点 6 和 4 的最近公共祖先: " + (res2 != null ? res2.val : "null")); // 预期输出: 5

        // 测试用例3：p=2, q=4，LCA应为 2 (节点本身也是自己的祖先)
        TreeNode p3 = root.left.right;
        TreeNode q3 = root.left.right.right;
        TreeNode res3 = lca.lowestCommonAncestor(root, p3, q3);
        System.out.println("节点 2 和 4 的最近公共祖先: " + (res3 != null ? res3.val : "null")); // 预期输出: 2

        // 3. 测试方法二：存储父节点法
        System.out.println("\n=== 测试方法二：存储父节点法 ===");
        // 注意：每次调用 lowestCommonAncestor1 前，需要清空状态（因为 parent 和 visited 是实例变量）
        // 这里重新 new 一个实例来避免状态污染
        lowestCommonAncestor lca2 = new lowestCommonAncestor();

        // 测试用例1：p=6, q=8，LCA应为 3
        TreeNode p4 = root.left.left;
        TreeNode q4 = root.right.right;
        TreeNode res4 = lca2.lowestCommonAncestor1(root, p4, q4);
        System.out.println("节点 6 和 8 的最近公共祖先: " + (res4 != null ? res4.val : "null")); // 预期输出: 3

        // 测试用例2：p=7, q=0，LCA应为 5
        TreeNode p5 = root.left.right.left;
        TreeNode q5 = root.right.left;
        TreeNode res5 = lca2.lowestCommonAncestor1(root, p5, q5);
        System.out.println("节点 7 和 0 的最近公共祖先: " + (res5 != null ? res5.val : "null")); // 预期输出: 5

    }
    /**
     *  深度优先遍历
     * */
    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
    /**
     *
     * */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

}
