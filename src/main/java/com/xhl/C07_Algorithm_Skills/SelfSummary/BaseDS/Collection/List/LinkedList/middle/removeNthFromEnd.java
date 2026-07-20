package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-06-25 00:36
 * @Description: 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class removeNthFromEnd {
    static void main() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next =new ListNode(3);
        head.next.next.next =new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n= 2;
        removeNthFromEnd rne = new removeNthFromEnd();
        ListNode node = rne.removeNthFromEnd(head, n);
        while (node!=null){
            System.out.print(node.val+" ");
            node = node.next;
        }
    }

    /**
     *  方法一：模拟
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        // 构造虚拟机节点
        ListNode cur = dummy;
        // 当遍历到第 L−n+1 个节点时，它就是我们需要删除的节点。
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    /**
     *  获取链表的长度
     * */
    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    /**
     *  方法二：栈
     * */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dumNode = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur =dumNode;
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.peek();
        pre.next = pre.next.next;
        ListNode ans = dumNode.next;

        return ans;
    }
    /**
     *  方法三：双指针  使用两个指针 first 和 second 同时对链表进行遍历
     * */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 构造 哑节点
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        //初始时将 second 指向哑节点
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        //  first 比 second 超前了 n 个节点 当 first 遍历到链表的末尾时
        //  second 的下一个节点就是我们需要删除的节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

}
