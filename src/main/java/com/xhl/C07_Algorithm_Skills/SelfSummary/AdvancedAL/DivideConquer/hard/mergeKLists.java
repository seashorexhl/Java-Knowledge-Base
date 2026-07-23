package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DivideConquer.hard;

import java.util.PriorityQueue;

/**
 * @Author: xhl
 * @Date: 2026-07-02 15:47
 * @Description:    23. 合并 K 个升序链表
 */
public class mergeKLists {
    public static void main(String[] args) {
        // 1. 构建测试用例数据
        // 用例1: [[1,4,5],[1,3,4],[2,6]]
        ListNode[] lists1 = new ListNode[]{
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };
        // 用例2: 包含空链表 []
        ListNode[] lists2 = new ListNode[]{
                null,
                new ListNode(1, new ListNode(2, new ListNode(3))),
                null
        };
        // 用例3: 空输入 []
        ListNode[] lists3 = new ListNode[]{};
        // 2. 创建实例并测试三种方法
        // 2. 创建实例并测试三种方法
        mergeKLists mk = new mergeKLists();

        System.out.println("=== 测试用例 1: [[1,4,5],[1,3,4],[2,6]] ===");
        System.out.println("方法一(顺序合并): " + listToString(mk.mergeKLists(lists1)));
        System.out.println("方法二(分治合并): " + listToString(mk.mergeKLists1(lists1)));
        System.out.println("方法三(优先队列): " + listToString(mk.mergeKLists2(lists1)));

        System.out.println("\n=== 测试用例 2: 包含空链表 ===");
        System.out.println("方法一(顺序合并): " + listToString(mk.mergeKLists(lists2)));
        System.out.println("方法二(分治合并): " + listToString(mk.mergeKLists1(lists2)));
        System.out.println("方法三(优先队列): " + listToString(mk.mergeKLists2(lists2)));

        System.out.println("\n=== 测试用例 3: 空输入 ===");
        System.out.println("方法一(顺序合并): " + listToString(mk.mergeKLists(lists3)));
        System.out.println("方法二(分治合并): " + listToString(mk.mergeKLists1(lists3)));
        System.out.println("方法三(优先队列): " + listToString(mk.mergeKLists2(lists3)));
    }

    /**
     * 辅助方法：将链表转换为字符串，方便打印查看
     */
    public static String listToString(ListNode head) {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(", ");
            }
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }
    /**
     *  方法一：顺序合并
     * */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }
    /**
     *  递归合并
     * */
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
    /**
     *  方法二：分治合并
     * */
    public ListNode mergeKLists1(ListNode[] lists) {
        return merge1(lists, 0, lists.length - 1);
    }

    public ListNode merge1(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge1(lists, l, mid), merge1(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists1(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<Status> queue = new PriorityQueue<>();

        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    /**
     *  方法三：使用优先队列合并
     * */
    static class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status status2) {
            // 2. 核心修复：先比较值，如果值相等，比较对象的哈希码，防止优先队列报错
            if (this.val != status2.val) {
                return this.val - status2.val;
            }
            return System.identityHashCode(this.ptr) - System.identityHashCode(status2.ptr);
        }
    }

}
