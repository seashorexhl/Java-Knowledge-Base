package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-09 11:14
 * @Description: 环形链表
 */
public class hasCycleSolution {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        hasCycleSolution hcs = new hasCycleSolution();
        boolean b = hcs.hasCycle(head);
        System.out.println("链表是否有环："+b);


        boolean b1 = hcs.hasCycle2(head);
        System.out.println("链表是否有环："+b1);
    }
    // 方法一:哈希表
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }
    // 方法二:快慢指针
    public boolean hasCycle2(ListNode head) {
        // 1.首先判空
        if (head == null || head.next == null) {
            return false;
        }
        // 2.快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
