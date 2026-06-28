package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.List.LinkedList;

import java.util.LinkedList;
import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-03 01:34
 * @Description: 链表 与双链表 常用 API 及生命周期
 */
public class LinkedListLifeStyle {
    public static void main(String[] args) {
        //初始化 一个 存储 Int 类型的双链表
        LinkedList<Integer> nums = new LinkedList<>();
        // 初始化 一个存储String 类型的双链表
        LinkedList<String> Strings = new LinkedList<>();
        /*常用方法*/
        //判断链表是否为空
        System.out.println("判断链表是否为空：" + nums.isEmpty());

        // 判断链表 是否存在 元素 O
        System.out.println("是否存在元素 0：" + nums.contains("O"));

        /*增删改查*/
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.addFirst(0); //addFirst addLast
        nums.removeLast(); //removeLast removeFirst
        /*遍历*/
        // 1. 增强 for 循环（推荐，简洁）
        System.out.println("增强 for 循环:");
        for (Integer item : nums) {
            System.out.print(item + " ");
        }
        // 2. 迭代器遍历（推荐，适合遍历中删除元素）
        System.out.println("\n迭代器遍历:");
        Iterator<Integer> iterator = nums.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        // 3. Java 8 forEach（推荐，函数式风格）
        System.out.println("\nforEach:");
        nums.forEach(item -> System.out.print(item + " "));

        // 4.严禁使用 普通 For 循环，性能极差
        System.out.println();
        //返回链表的个数
        System.out.println("链表的个数" + nums.size());


    }
    /**
     * 1.链表的遍历
     */
    // 1.递归遍历
    void traverse(ListNode head) {
        // 1. 递归终止条件
        if (head == null) {
            return;
        }

        // 2. 处理当前节点（前序遍历）
        System.out.println(head.val);

        // 3. 递归处理下一个节点
        traverse(head.next);

        // 4. 处理当前节点（后序遍历，如：逆序打印链表）
        System.out.println(head.val);
    }
    // 2. 虚拟头节点模板（Dummy Node）⭐⭐⭐
    public ListNode Traverse(ListNode head) {
        ListNode dummy = new ListNode(-1); // 创建一个虚拟头节点
        dummy.next = head;                 // 虚拟头节点指向真实头节点

        ListNode current = dummy;          // 从虚拟头节点开始遍历
        while (current.next != null) {     // 注意：这里判断的是 current.next 是否为空
            // 在这里处理 current.next 节点
            // 例如：if (current.next.val == target) current.next = current.next.next;

            current = current.next; // 指针后移
        }

        return dummy.next; // 返回真正的头节点
    }

    // 3. 快慢指针模板（双指针）⭐⭐⭐
    public ListNode SFTraverse(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // 循环条件通常是 fast != null && fast.next != null
        while (fast != null && fast.next != null) {
            slow = slow.next;        // 慢指针走 1 步
            fast = fast.next.next;   // 快指针走 2 步

            // 在这里可以做一些判断，比如：
            // if (slow == fast) return true; // 判断是否有环
        }

        // 循环结束后：
        // 如果链表有环，slow 和 fast 会相遇
        // 如果链表无环，slow 正好停在链表中点（偶数时为中间偏右）
        return head;
    }
}
