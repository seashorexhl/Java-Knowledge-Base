package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.hard;


import com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.ListNode;

/**
 * @Author: xhl
 * @Date: 2026-06-25 00:35
 * @Description: 25. K 个一组翻转链表
 *  方法：模拟
 */
public class reverseKGroup {
    static void main() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;
        reverseKGroup rk = new  reverseKGroup();
        ListNode node = rk.reverseKGroup(head, k);
        while (node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
    }
    /**
     *  方法一：模拟
     *  将链表按 k 个节点为一组进行切割，对每一组进行翻转，然后再拼接回去。
     * */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. 创建一个虚拟头节点（哨兵节点），简化边界处理（如翻转包含原头节点的组）
        ListNode hair = new ListNode(0);
        hair.next = head;
        // pre 指针：始终指向当前待翻转子链表的【前一个】节点
        ListNode pre = hair;
        // 2. 遍历链表，每次处理 k 个节点
        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                // 3. 检查剩余节点数是否 >= k
                // 如果不足 k 个，直接返回结果（不翻转尾部不足 k 个的节点）

                if (tail == null) {
                    return hair.next;
                }
            }
            // 4. 记录下一组待翻转链表的起始节点（当前组 tail 的下一个节点）

            ListNode nex = tail.next;
            // 5. 翻转当前 [head, tail] 区间的 k 个节点
            // myReverse 返回一个数组：[新的头节点, 新的尾节点]

            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];// 翻转后的头节点（原 tail）
            tail = reverse[1]; // 翻转后的尾节点（原 head）
            // 6. 将翻转后的子链表重新接回原链表
            pre.next = head;// 前驱节点指向新的头节点
            tail.next = nex;// 新的尾节点指向下一组的起始节点
            // 7. 移动指针，准备处理下一组
            pre = tail;// pre 移动到当前组的尾节点（作为下一组的前驱）
            head = tail.next;// head 移动到下一组的起始节点
        }

        return hair.next;// 返回虚拟头节点的下一个节点，即真正的头节点
    }
    /**
     *  辅助方法：翻转指定区间 [head, tail] 的链表节点
     * */
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        // prev 初始化为 tail 的下一个节点，作为翻转后尾节点的 next 指向

        ListNode prev = tail.next;
        // p 用于遍历当前区间

        ListNode p = head;
        // 当 p 到达 tail 时停止（即翻转完当前区间的所有节点）

        while (prev != tail) {
            ListNode nex = p.next;// 暂存下一个节点，防止断链
            p.next = prev;// 当前节点指向前一个节点（实现反转）
            prev = p; // prev 向前移动
            p = nex; // prev 向前移动
        }
        // 翻转完成后：原来的 tail 变成了头，原来的 head 变成了尾
        return new ListNode[]{tail, head};
    }

}
