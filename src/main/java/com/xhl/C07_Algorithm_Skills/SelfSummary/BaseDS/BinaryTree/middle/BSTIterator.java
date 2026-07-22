package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.BinaryTree.middle;

import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:39
 * @Description: 173. 二叉搜索树迭代器 ⭐⭐⭐
 *  方法一:扁平化
 *  方法二:迭代 见 BinarySTIterator
 */
public class BSTIterator {

    static void main() {
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
     * ================== 方法一：扁平化（预处理） ==================
     * 核心思想：在构造时直接通过中序遍历将树转化为有序数组，
     * 后续的 next() 和 hasNext() 退化为数组的指针操作。
     * 优点：next() 和 hasNext() 时间复杂度严格 O(1)。
     * 缺点：构造时空间复杂度 O(N)，且初始化耗时 O(N)。
     */

    /**
     * 指向当前待返回元素的数组索引指针。
     * 每次调用 next() 时，返回当前索引的值，并将指针后移一位。
     * */
    private int idx;
    /**
     * 存储中序遍历结果的有序数组。
     * 因为二叉搜索树的中序遍历结果是升序的，所以数组天然有序。
     */
    private List<Integer> arr;
    /**
     * 构造函数：初始化迭代器
     * 在创建对象时，直接调用递归中序遍历，将整棵树"拍平"存入数组中。
     */
    public BSTIterator(TreeNode root) {
        idx = 0; // 初始化指针指向数组的第一个元素
        arr = new ArrayList<>();
        inorderTraversal(root, arr);// 执行中序遍历，填充数组
    }
    /**
     * 返回下一个最小的数
     * 直接通过索引获取数组元素，并将指针后移一位。
     * 时间复杂度：O(1)
     */
    public int next() {
        return arr.get(idx++);
    }

    /**
     * 判断是否还有下一个元素
     * 只要指针没有越界，就说明数组中还有未访问的元素。
     * 时间复杂度：O(1)
     */
    public boolean hasNext() {
        return idx < arr.size();
    }
    /**
     * 辅助方法：递归中序遍历
     * 遍历顺序：左子树 -> 根节点 -> 右子树
     * 将沿途访问到的节点值依次追加到列表中。
     */
    private void inorderTraversal(TreeNode root, List<Integer> arr) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, arr);// 1. 递归遍历左子树
        arr.add(root.val);// 2. 处理当前节点（加入数组）
        inorderTraversal(root.right, arr); // 3. 递归遍历右子树
    }

}
