package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-25 00:53
 * @Description: 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class rotateRight {
    static void main() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 2;

        rotateRight rr = new rotateRight();
        ListNode node = rr.rotateRight(head, k);
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
    /*方法一：闭合为环*/
    public ListNode rotateRight(ListNode head, int k) {
        // 边界条件判断
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        // 1. 遍历链表，计算链表的总长度 n
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        // 2. 计算实际需要向后移动的步数
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        // 3. 将链表首尾相连，形成一个环
        iter.next = head;
        // 4. 从环的当前位置（原尾节点）向后走 add 步，到达新链表的尾节点
        while (add-- > 0) {
            iter = iter.next;
        }
        // 5. 断开环，形成旋转后的新链表
        ListNode ret = iter.next;
        iter.next = null;
        // 返回新链表的头节点
        return ret;
    }
}
