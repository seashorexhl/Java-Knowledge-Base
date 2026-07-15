package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-06-04 13:12
 * @Description: 树的遍历 基础框架 ⭐⭐⭐⭐⭐
 *  前序 中序 后序遍历 递归+迭代
 *  层序遍历 BFS 广度优先遍历
 *
 */
public class TraverseTree {
    public static void main(String[] args) {
        /*构建树的节点*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        TraverseTree obj = new TraverseTree();

        // 前序遍历
        System.out.println("--------------------递归实现-------------------------");
        System.out.println("前序遍历:");
        obj.preorderRecursive(root);
        System.out.println("前序遍历递归实现:");

        // 中序遍历
        System.out.println("中序遍历:");
        obj.inorderRecursive(root);
        System.out.println("中序遍历递归实现:");

        // 后序遍历
        System.out.println("后序遍历:");
        obj.postorderRecursive(root);
        System.out.println("后序遍历递归实现:");


        System.out.println("--------------------非递归实现-------------------------");
        obj.preorderIterative(root);
        System.out.println("前序遍历非递归实现:");
        obj.inorderIterative(root);
        System.out.println("中序遍历非递归实现:");
        obj.postorderIterative(root);
        System.out.println("后序遍历非递归实现:");


        System.out.println("--------------------层序遍历---------------------------:");
        //层序遍历 BFS 广度优先遍历
        System.out.println("层序遍历:");
        obj.levelOrder(root);
    }
    /**
     * A.递归实现 前序遍历
     * */
    //1.递归实现 ：先访问当前节点，再依次递归左右子树。
    public void preorderRecursive(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");      // 访问根节点
        preorderRecursive(root.left);          // 遍历左子树
        preorderRecursive(root.right);         // 遍历右子树
    }
    /** A.递归实现 中序遍历*/
    // 2.递归实现 ：先递归左子树，再访问当前节点，最后递归右子树。
    public void inorderRecursive(TreeNode root) {
        if (root == null) return;
        inorderRecursive(root.left);           // 遍历左子树
        System.out.print(root.val + " ");      // 访问根节点
        inorderRecursive(root.right);          // 遍历右子树
    }
    /** A.递归实现 后序遍历*/
    // 3.递归实现 ：先递归左右子树，最后访问当前节点。
    public void postorderRecursive(TreeNode root) {
        if (root == null) return;
        postorderRecursive(root.left);         // 遍历左子树
        postorderRecursive(root.right);        // 遍历右子树
        System.out.print(root.val + " ");      // 访问根节点
    }
    /**
     *  B.非递归实现 前序遍历 ：利用栈（LIFO）。先将根节点入栈，循环弹出并访问。为了保证出栈时是“先左后右”，
    */
    // 入栈时必须先压入右子节点，再压入左子节点。
    public void preorderIterative(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");  // 访问根节点
            if (node.right != null) stack.push(node.right); // 右孩子先入栈
            if (node.left != null) stack.push(node.left);   // 左孩子后入栈
        }
    }

    /**
     * B.非递归实现 中序遍历：使用指针辅助。不断将当前节点及其左子节点压入栈中，直到左子节点为空。然后弹出栈顶元素
    **/
    // 进行访问，并将指针转向该节点的右子树继续上述过程。
    public void inorderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);           // 沿左子树一路压栈
                current = current.left;
            }
            current = stack.pop();             // 弹出最左侧节点
            System.out.print(current.val + " ");// 访问根节点
            current = current.right;           // 转向右子树
        }
    }

    /**
     *  B.非递归实现 后序遍历：由于需要确保左右子树都处理完才能访问根节点，逻辑最为复杂。一种经典的巧妙变通方法是：
     */
    // 按照“根→右→左”的顺序进行类似前序的遍历，将结果存入另一个栈中，最后依次弹出即可得到“左→右→根”的结果。
    public void postorderIterative(TreeNode root) {
        if (root == null) return; //判空
        Stack<TreeNode> stack1 = new Stack<>(); // 用于遍历
        Stack<TreeNode> stack2 = new Stack<>(); // 用于反转顺序

        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);                  // 按 根->右->左 存入stack2
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        // 从stack2弹出即为 左->右->根
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }
    /**
     *  C.广度优先遍历 (BFS) 层序遍历
     *  */
     /*层序遍历不使用递归，而是借助队列（Queue）数据结构来实现。每次从队首取出节点访问，
     并将其非空的左右子节点依次加入队尾。*/
    public void levelOrder(TreeNode root) {

        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); //根节点 入队

        // 循环
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); //取出队首节点
            System.out.print(node.val + " "); //访问当前节点
            if (node.left != null) queue.offer(node.left);//左子节点入队
            if (node.right != null) queue.offer(node.right);//右子节点入队
        }
    }
}
