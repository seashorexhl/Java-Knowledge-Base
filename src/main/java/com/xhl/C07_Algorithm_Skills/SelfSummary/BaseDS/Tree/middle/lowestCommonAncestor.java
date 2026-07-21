package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-07-02 13:19
 * @Description: 236. 二叉树的最近公共祖先
 *  递归 + 深度优先
 */
public class lowestCommonAncestor {


    static void main() {
        lowestCommonAncestor lca = new lowestCommonAncestor();
        /*
         * 构建测试树：
         *             3
         *           /   \
         *          5     1
         *         / \   / \
         *        6   2 0   8
         *           / \
         *          7   4
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // 用例1：p=5, q=1，LCA 应该是 3
        TreeNode p1 = root.left;
        TreeNode q1 = root.right;
        test(lca, root, p1, q1, 3, "用例1: 左右子树的根");

        // 用例2：p=6, q=4，LCA 应该是 5
        TreeNode p2 = root.left.left;
        TreeNode q2 = root.left.right.right;
        test(lca, root, p2, q2, 5, "用例2: 同一子树下的节点");

        // 用例3：p=6, q=7，LCA 应该是 5
        TreeNode p3 = root.left.left;
        TreeNode q3 = root.left.right.left;
        test(lca, root, p3, q3, 5, "用例3: 跨左右分支");

        // 用例4：p=5, q=4，LCA 应该是 5（其中一个节点就是祖先）
        TreeNode p4 = root.left;
        TreeNode q4 = root.left.right.right;
        test(lca, root, p4, q4, 5, "用例4: 节点自身就是LCA");

        // 用例5：p=3, q=8，LCA 应该是 3
        TreeNode p5 = root;
        TreeNode q5 = root.right.right;
        test(lca, root, p5, q5, 3, "用例5: root本身就是LCA");
    }
    /**
     *  测试 方法
     * */
    private static void test(lowestCommonAncestor lca, TreeNode root, TreeNode p, TreeNode q, int expected, String desc) {
        TreeNode res1 = lca.lowestCommonAncestor(root, p, q);
        TreeNode res2 = lca.lowestCommonAncestor1(root, p, q);

        boolean pass1 = (res1 != null && res1.val == expected);
        boolean pass2 = (res2 != null && res2.val == expected);

        System.out.printf("[%s] 期望: %d | 方法一结果: %d (%s) | 方法二结果: %d (%s)%n",
                desc, expected,
                res1 != null ? res1.val : -1, pass1 ? "PASS" : "FAIL",
                res2 != null ? res2.val : -1, pass2 ? "PASS" : "FAIL");
    }

    /**
     *  方法二：存储父节点
     * */
    Map<Integer, TreeNode> parent = new HashMap<>();

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
    Set<Integer> visited = new HashSet<>();
    private TreeNode ans;

    /**
     *  方法一：递归
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    /**
     *  深度优先 DFS
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
     *  方法二：递归
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
