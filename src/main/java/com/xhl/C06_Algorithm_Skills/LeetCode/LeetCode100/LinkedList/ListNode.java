package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-04-22 15:02
 * @Description: 链表 节点的构建
 */
public class ListNode {
    int val;
    public ListNode next;
    ListNode(){

    }
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
