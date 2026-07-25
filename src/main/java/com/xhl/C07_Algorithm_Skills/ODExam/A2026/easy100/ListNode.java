package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100;

/**
 * @Author: xhl
 * @Date: 2026-07-25 13:01
 * @Description: ListNode 节点构建
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int x) { val = x; }
    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}
