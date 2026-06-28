package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.List.LinkedList.middle;

import com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-25 00:52
 * @Description: 82. 删除排序链表中的重复元素 II
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，
 * 只留下不同的数字 。返回 已排序的链表 。
 */
public class deleteDuplicates {
    static void main() {
        ListNode head = new ListNode(1);
        head.next =new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next =new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next =new ListNode(5);

        deleteDuplicates dd = new deleteDuplicates();
        ListNode node = dd.deleteDuplicates(head);
        System.out.println("一次遍历");
        while (node!= null){
            System.out.print(node.val + " ");
            node = node.next;
        }

    }
    //   方法一：一次遍历
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null){
            return  head;
        }
        ListNode dumHead = new ListNode(0,head);
        ListNode cur = dumHead;

        while (cur.next != null && cur.next.next !=null){
            if(cur.next.val == cur.next.next.val){
                int x =cur.next.val;
                while (cur.next !=null && cur.next.val == x){
                    cur.next = cur.next.next;
                }
            }else {
                cur = cur.next;
            }
        }
        return  dumHead.next;
    }
}
