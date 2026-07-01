package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.easy;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-22 08:30
 * @Description: 100 相同的树
 */
public class isSameTree {
    static void main() {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode  q  =  new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        isSameTree ist  = new isSameTree();
        boolean sameTree = ist.isSameTree(p, q);
        System.out.println(sameTree);

        System.out.println("深度优先遍历：" +ist.isSameTreeDFS(p, q));

        isSameTree ist1 = new isSameTree();
        System.out.println("广度优先遍历：" + ist1.isSameTreeBFS(p, q));
    }

    // 原始 递归 实现
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean res = false;
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        res = isSameTree(p.left, q.left) &&  isSameTree(p.right, q.right);

        return res;
    }
    //  方法一：深度优先搜索
    public boolean isSameTreeDFS(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
    //  方法二：广度优先搜索
    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        boolean res = false;
        // 1.处理边界条件
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        // 2.使用 合适 数据结构
        Queue<TreeNode> que1 = new LinkedList<>();
        Queue<TreeNode> que2 = new LinkedList<>();
        que1.offer(p);
        que2.offer(q);
        // 3. 核心循环：同时遍历两棵树，逐层逐节点进行比较
        while (!que1.isEmpty() && !que2.isEmpty()) {
            TreeNode node1 = que1.poll();
            TreeNode node2 = que2.poll();
            // 核心比较1：如果当前两个节点的值不相等，说明树的结构或内容不同，直接返回 false
            if (node1.val != node2.val) {
                return false;
            }
            // 获取当前节点的左右子节点，方便后续进行结构对比
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            TreeNode right1 = node1.right;
            TreeNode right2 = node2.right;
            // 核心比较2：利用异或运算（^）检查左子节点的“空指针状态”是否一致
            // 如果一棵树的左子节点为空，而另一棵树的左子节点不为空，说明两棵树的结构不同，直接返回 false
            if(left1==null ^ left2==null) return  false;
            // 核心比较3：同理，利用异或运算检查右子节点的“空指针状态”是否一致
            // 确保两棵树的右子树结构也完全对称
            if(right1==null^ right2==null) return  false;
            // 将非空的左、右子节点分别加入对应的队列，准备进行下一层的比较
            if (left1!=null){
                que1.offer(left1);
            }
            if (left2!=null){
                que2.offer(left2);
            }
            if (right1!=null){
                que1.offer(right1);
            }
            if (right2!=null){
                que2.offer(right2);
            }

        }
        // 循环结束后的收尾判断：
        // 如果两棵树完全相同，那么两个队列必然同时被消耗完（同时为空）。
        // 如果一棵树是另一棵树的子树（比如 p 遍历完了，但 q 还有剩余节点），
        // 此时会有一个队列不为空，说明两棵树节点数量/结构不同，返回 false。
        res = que1.isEmpty() && que2.isEmpty();
        return res;
    }

}