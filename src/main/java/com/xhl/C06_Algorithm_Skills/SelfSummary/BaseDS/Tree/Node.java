package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:02
 * @Description:
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
