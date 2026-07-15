package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-24 22:31
 * @Description: 206 反转链表
 */
public class reverseList {
    static void main() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        reverseList rl = new reverseList();
        ListNode node = rl.reverseList(head);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }

    }

    //方法一：迭代  将当前节点的 next 指针改为指向前一个节点
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 方法二：递归
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return  head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return  newHead;
    }
}
