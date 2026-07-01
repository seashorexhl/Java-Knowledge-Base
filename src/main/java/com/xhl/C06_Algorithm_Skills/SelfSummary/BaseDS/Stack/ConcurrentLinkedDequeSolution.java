package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Stack;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Author: xhl
 * @Date: 2026-06-11 06:16
 * @Description: ConcurrentLinkedDeque 基于并发链表双端队列的解决方案
 * 它的 API 与普通的 Deque 完全一致，支持从两端高效地进行增删查改：
 */
/**
 *  典型适用场景
 * 由于其高并发性能和双端操作的灵活性，它非常适合以下业务场景：
 * 工作窃取 (Work-Stealing) 算法：在自定义线程池中，每个线程维护一个本地队列。空闲线程可以从自己的头部取任务 (pollFirst)，或者从繁忙线程的尾部“窃取”任务 (pollLast)。
 * 多生产者-多消费者系统：生产者可以自由选择向头部或尾部添加任务，消费者也可以灵活选择消费端。
 * 实时事件/消息缓冲：例如在线聊天室的消息暂存区，新的高优先级消息可以加到头部，旧消息从尾部按顺序处理。
 * 并发缓存实现：如 LRU 缓存，最近使用的节点加到头部，淘汰策略从尾部移除。
 * */
public class ConcurrentLinkedDequeSolution {
    public static void main(String[] args) {

        // 创建实例（无需指定初始容量）
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();

        // 头部操作
        deque.offerFirst("Task-A"); // 添加到头部
        String head = deque.pollFirst(); // 从头部移除并返回

        // 尾部操作
        deque.offerLast("Task-B"); // 添加到尾部
        String tail = deque.pollLast(); // 从尾部移除并返回
    }
}
