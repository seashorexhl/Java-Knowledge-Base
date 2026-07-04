package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree;

/**
 * @Author: xhl
 * @Date: 2026-06-04 22:38
 * @Description:  222 完全二叉树 的节点个数 ⭐⭐⭐⭐⭐
 */
public class countNodesSolutions {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        countNodesSolutions cns = new countNodesSolutions();
        System.out.println("完全二叉树的节点数为："+ cns.countNodes(root));

    }

    // 二分查找+位运算
    public int countNodes(TreeNode root) {
        // 1. 基础边界条件：如果根节点为空，直接返回0
        if (root == null) {
            return 0;
        }
        // 2. 计算完全二叉树的深度（高度）
        // 完全二叉树的特性决定了：一直沿着左子节点往下走，就能得到树的总层数
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        // 3. 确定最后一层节点编号的搜索范围 [low, high]
        // 如果深度为 level，那么节点总数在 [2^level, 2^(level+1) - 1] 之间
        // 例如：level=2，节点数范围是 [4, 7]（即二进制的 100 到 111）
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        // 4. 在 [low, high] 范围内进行二分查找，寻找最后一个存在的节点编号
        while (low < high) {
            // 取右中位数，防止死循环。
            // 当 low 和 high 相邻时，(high - low + 1) / 2 保证 mid 偏向 high
            int mid = (high - low + 1) / 2 + low;
            // 检查编号为 mid 的节点是否存在
            if (exists(root, level, mid)) {
                low = mid; // 如果存在，说明目标在右半边（包含 mid），收缩左边界
            } else {
                high = mid - 1; // 如果不存在，说明目标在左半边（不包含 mid），收缩右边界
            }
        }
        //  循环结束时，low == high，即为最后一个存在的节点编号，也就是节点总数
        return low;
    }
    // 是否存在
    public boolean exists(TreeNode root, int level, int k) {
        // 核心思想：利用节点编号的二进制表示来寻路！
        // 去掉编号 k 的最高位 1 后，剩下的二进制位就是路径：
        // 遇到 0 往左走，遇到 1 往右走。
        // bits 初始值为 1 << (level - 1)，代表一个掩码，用于从高位到低位逐位提取 k 的二进制位
        // 例如：level=3, k=5(101)，bits 初始为 100
        int bits = 1 << (level - 1);
        TreeNode node = root;
        // 从根节点出发，根据掩码逐层往下走
        while (node != null && bits > 0) {
            // 使用按位与 (&) 检查当前位是 0 还是 1
            if ((bits & k) == 0) {
                node = node.left; // 当前位为 0，向左子节点走
            } else {
                node = node.right; // 当前位为 1，向右子节点走
            }
            // 掩码右移一位，准备检查下一位
            bits >>= 1;
        }
        // 走完 level 步后，如果 node 不为空，说明该节点存在
        return node != null;
    }

}
