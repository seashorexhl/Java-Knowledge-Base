package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.TwoPointers;

import com.xhl.Algorithm_Skills.LeetCode.LeetCode100.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-15 02:46
 * @Description: 1. 快慢指针（Floyd 判圈算法）
 */
/**
 *  应用场景：
 *      链表环检测、寻找链表中点、删除链表倒数第 N 个节点等
 * */
public class SlowFastPointer{
    static void main() {

    }

    // 检测链表是否有环
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // 慢指针每次移动 1 步
            fast = fast.next.next;      // 快指针每次移动 2 步

            if (slow == fast) {         // 两指针相遇说明有环
                return true;
            }
        }
        return false;
    }

    // 寻找链表中点
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // 奇数长度返回中点，偶数长度返回中间偏右
    }
}
