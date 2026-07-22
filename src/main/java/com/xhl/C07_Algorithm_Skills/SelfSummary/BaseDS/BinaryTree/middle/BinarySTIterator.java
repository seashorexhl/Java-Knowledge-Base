package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-07-20 17:21
 * @Description:  173. 二叉搜索树迭代器  ⭐⭐⭐
 * 方法二：迭代
 */
public class BinarySTIterator {
    /**
     *  方法二：迭代
     * */
    // 当前正在处理的节点指针。
    private TreeNode cur;

    // 用栈来保存"待处理"的祖先节点。
    private Deque<TreeNode> stack;

    //构造函数：初始化迭代器
    //     * 将根节点赋给 cur，并初始化一个空栈。
    //     * 注意：这里并没有在构造时就把整棵树压入栈，而是采用"按需压栈"的策略。
    public  BinarySTIterator(TreeNode root) {
        cur = root;
        stack = new LinkedList<>();
    }
    /**
     *  主函数
     * */
    static void main(String[] args) {
        // 1. 构建一棵简单的二叉搜索树用于测试
        //       7
        //      / \
        //     3   15
        //        /  \
        //       9    20
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        // 2. 初始化迭代器
        BinarySTIterator iterator = new BinarySTIterator(root);

        // 3. 测试 next() 和 hasNext()
        System.out.println("当前是否有下一个元素: " + iterator.hasNext()); // true
        System.out.println("next: " + iterator.next()); // 3
        System.out.println("next: " + iterator.next()); // 7
        System.out.println("next: " + iterator.next()); // 9

        // 4. 循环调用直到遍历结束
        while (iterator.hasNext()) {
            System.out.println("next: " + iterator.next()); // 15, 20
        }

        System.out.println("当前是否有下一个元素: " + iterator.hasNext()); // false
    }
    /**
     * 返回下一个最小的数（中序遍历的下一个节点）
     *
     * 执行流程：
     * 1. 一路向左：把从当前节点到最左叶子的所有节点压入栈中。
     * 2. 弹出栈顶：栈顶元素就是当前未访问的最小节点。
     * 3. 转向右子树：将 cur 指向弹出节点的右孩子，为下一次 next() 做准备。
     */
    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int ret = cur.val;
        cur = cur.right;
        return ret;
    }
    /**
     * 判断是否还有下一个元素
     *
     * 只要满足以下任一条件，就说明还有未访问的节点：
     * 1. cur != null：当前指针还指向一个未入栈的节点（通常是某个右子树）
     * 2. stack 不为空：栈中还有已入栈但尚未弹出的祖先节点
     */
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

}
