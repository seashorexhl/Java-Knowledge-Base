package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.easy;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-24 09:59
 * @Description: mergeTwoLists 21. 合并两个有序链表
 *  方法一：递归
 *  方法二：迭代
 */
public class mergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        mergeTwoLists mt = new mergeTwoLists();
        ListNode li = mt.mergeTwoLists(l1, l2);
        // 打印
        while (li!=null){
            System.out.print(li.val+" ");
            li = li.next;
        }
    }

    /**
     * 方法一：递归
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return  l2;
        }else if(l2 == null){
            return l1;
        } else if (l1.val <l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return  l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return  l2;
        }
    }
    /**
     *  方法二：迭代
     * */
    public  ListNode mergeTwoLists1(ListNode l1,ListNode l2){
        // 哨兵节点
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
