package com.xhl.Algorithm_Skills.LeetCode.LeetCode100.LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-06-18 11:34
 * @Description: 链表的 操作模板
 * @flag:  Obey The Rules,Keep a clear mind!
 * Acer 电脑 使用  win+. 来 实现 正确的标点符号。
 */

public class ListNodeSolution {
    static void main() {
        ListNode  head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNodeSolution sol = new ListNodeSolution();

        System.out.println("遍历反转链表：");
        sol.traverse(head);
        System.out.printf("反转链表");
    }
    /**
     *  1.通用 链表操作模板
     * */
    /**
     *  ① 反转链表（迭代法）
    */
    //  最经典的链表操作，利用三个指针不断改变 next 的指向。
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next; // 暂存下一个节点
            curr.next = prev;              // 反转指向
            prev = curr;                   // 指针前进
            curr = nextTemp;
        }
        return prev;
    }
    /**
     *  ② 寻找链表中间节点（快慢指针）
     * */
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // 偶数节点时返回第二个中间节点
    }
    /**
     *  ② 判断是否有环
     * */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;

        }
        return false;
    }
    /**
     *  ③ 判断是否有环 & 找环入口（Floyd 判圈算法）
     * */
    // 找环入口（相遇后，一个指针回起点，同速前进，再次相遇即为入口）
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
    /**
     * ④ 合并两个有序链表
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1); // 虚拟头节点，避免处理空指针
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        // 拼接剩余部分
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
    /**
     * ⑤ 删除倒数第 N 个节点（双指针间隔法）
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;

        // 让 first 先走 n 步
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        // 同时前进，直到 first 到达末尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 删除节点
        second.next = second.next.next;
        return dummy.next;
    }
    /**
     *  2.通用 遍历
     * */
    /**
     * ① 基础单向遍历
     * */
    public void traverse(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            // 处理当前节点 curr.val
            System.out.print(curr.val + " ");

            // 移动到下一个节点
            curr = curr.next;
        }
    }
    /**
     * ② 带索引的遍历（获取节点位置）
     * */
    public void traverseWithIndex(ListNode head) {
        ListNode curr = head;
        int index = 0;
        while (curr != null) {
            // index 即为当前节点的位置（从0开始）
            System.out.println("Index: " + index + ", Value: " + curr.val);

            curr = curr.next;
            index++;
        }
    }
    /**
     * ③ 反向遍历（利用递归或栈）
     * */
    /**
     *  方式 A：使用递归（利用系统调用栈，代码最简洁）
     * */
    public void reverseTraverse(ListNode head) {
        if (head == null) return;

        reverseTraverse(head.next); // 先走到最后

        // 在回溯时处理节点（此时是从尾到头的顺序）
        System.out.println(head.val);
    }

    /**
     * 方式B：使用显式栈（适合非递归场景）
     * */
    public void reverseTraverseWithStack(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode curr = head;

        // 1. 全部压栈
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        // 2. 依次弹栈处理
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            System.out.println(node.val);
        }
    }
    /**
     *  4. 寻找特定节点的遍历（查找）
     * */
    public ListNode findNode(ListNode head, int targetVal) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == targetVal) {
                return curr; // 找到目标，提前结束遍历
            }
            curr = curr.next;
        }
        return null; // 遍历结束未找到
    }
    /**
     *  5.双指针 遍历
     * */
    public void traverseWithPrev(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // 此时 prev 是 curr 的前一个节点
            // 可以在这里进行删除、插入等操作

            prev = curr;
            curr = curr.next;
        }
    }
}
