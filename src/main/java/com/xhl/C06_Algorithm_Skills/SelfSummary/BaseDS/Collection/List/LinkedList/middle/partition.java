package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.middle;


import com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-25 00:53
 * @Description:  86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
 * 使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class partition {
    public  static void main(String[] args) {
        ListNode  head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(5);

    }

    //    方法一：模拟

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
