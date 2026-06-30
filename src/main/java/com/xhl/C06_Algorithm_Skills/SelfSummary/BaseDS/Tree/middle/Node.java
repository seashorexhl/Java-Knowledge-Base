package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:02
 * @Description:  Node节点构建 针对 117. 填充每个节点的下一个右侧节点指针 II
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
