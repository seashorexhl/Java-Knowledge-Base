package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.middle;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-24 11:01
 * @Description: addTwoNumbers  2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，
 * 并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class addTwoNumbers {
    static void main() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        addTwoNumbers atn = new addTwoNumbers();
        ListNode head = atn.addTwoNumbers(l1, l2);
        System.out.println("方法一：个人方法 使用 Queue");
        printListAsArray(head);
        /*方法二 模拟，虚拟头节点优化*/
        ListNode node2 = atn.addTwoNumbers2(l1, l2);
        System.out.println("方法二 模拟，虚拟头节点优化:");
        printListAsArray(node2);

    }
    /**
     *  通用 ：打印数组
     * */
    public static void printListAsArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;

        // 只需一次遍历，动态添加元素
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        // 将 ArrayList 转为原生数组并打印
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 个人方法 使用 Queue
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null && l2==null){
            return  null;
        }
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        while (l1 !=null){
            q1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            q2.add(l2.val);
            l2 =l2.next;
        }


        List<Integer> li = new LinkedList();
        int count = 0;
        while (!q1.isEmpty() || !q2.isEmpty()){

            int a = !q1.isEmpty() ? q1.poll() : 0;
            int b = !q2.isEmpty() ? q2.poll() : 0;
            int i =a+b+count;

            if (i<=9){
                li.add(i);
                count =0;
            }else {
                count =1;
                i = i%10;
                li.add(i);
            }

        }
        // 5. 修复：处理最高位进位（例如 999 + 1 = 1000，最后多出来的 1）
        if (count == 1) {
            li.add(1);
        }

        // 6. 修复：题目要求返回 ListNode，这里将 List 转换为链表
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : li) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
    /**
     *  模拟
     * */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
    /**
     *  方法：虚拟头节点优化
     * */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 1. 创建一个虚拟头节点，值为0，简化边界处理
        ListNode dummy = new ListNode(0);
        // 2. 使用 tail 指针追踪结果链表的末尾
        ListNode tail = dummy;
        int carry = 0;

        while (l1 != null || l2 != null) {
            // 获取当前位的值，如果链表已经走完则补0
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;

            int sum = n1 + n2 + carry;

            // 3. 计算当前位的个位数，并直接挂在 tail 后面
            tail.next = new ListNode(sum % 10);
            tail = tail.next; // tail 指针后移

            // 4. 计算进位
            carry = sum / 10;

            // 移动原链表指针
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 5. 处理最高位进位
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        // 6. 返回结果链表（跳过虚拟头节点）
        return dummy.next;
    }
}
