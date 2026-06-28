package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.List.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-06-24 02:15
 * @Description: 链表 节点的构建
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(){

    }
    public ListNode(int x) {
        val = x;
    }
    public  ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}
