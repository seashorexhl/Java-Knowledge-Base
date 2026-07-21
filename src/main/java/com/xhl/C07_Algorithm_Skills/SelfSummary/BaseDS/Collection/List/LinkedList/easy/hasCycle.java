package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-24 09:37
 * @Description: 141 环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 */
public class hasCycle {
    static void main() {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;

        int pos = -1;
        hasCycle hc = new hasCycle();
        boolean b = hc.hasCycle(head);
        System.out.println("方法一判断是否有环：" + b);
        System.out.println("方法二判断是否有环：" + hc.hasCycle1(head));
    }

    /**
     *   方法一：哈希表
     * */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }

        return false;
    }

    /**
     *  方法二：快慢指针 依靠 快慢指针 和
     * */
    public boolean hasCycle1(ListNode head) {
        if (head ==null || head.next == null){
            return  false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if(fast == null || fast.next == null){
                return  false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return  true;
    }
}
