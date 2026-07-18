package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Queue.baseQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-06-15 14:01
 * @Description: 二、双端队列（Deque） 数组双端队列
 * 动态循环数组实现的高性能双端队列
 * 当作为栈使用时，它比传统的 Stack 类更快；当作为队列使用时，它比 LinkedList 更快。
 */
/**
 *  核心应用场景
 *  替代 Stack 和 LinkedList
 * 由于 ArrayDeque 基于数组实现，内存连续，对 CPU 缓存更友好。相比 LinkedList，它在头尾插入、删除操作上性能更优，且内存占用更少（节省约 50%）；相比 Stack，它避免了不必要的线程同步开销。
 * 广度优先搜索（BFS）算法
 * 在图或树的广度优先搜索中，ArrayDeque 是理想的队列实现。其 offerLast 和 pollFirst 操作完美契合 BFS 的 FIFO 特性，且均摊时间复杂度为 O(1)。
 * 滑动窗口问题
 * 在算法题（如“滑动窗口最大值”）中，通常需要维护一个窗口内的最值。ArrayDeque 的双端特性可以高效地在窗口滑动时移除过期元素，并维持内部元素的单调性，总体时间复杂度可优化至 O(n)。
 * 撤销操作（Undo）与历史记录
 * 作为历史记录栈使用，例如模拟浏览器的前进和后退功能，或编辑器中的撤销/重做操作。
 * */
public class ArrayDequeSolution {

    static void main() {
        /**
         *  1. 作为栈（Stack）使用（LIFO 后进先出）
         * */
        Deque<String> stack = new ArrayDeque<>();

        // 压栈操作
        stack.push("Java");
        stack.push("Python");

        // 弹栈操作
        while (!stack.isEmpty()) {
            System.out.println(stack.pop()); // 输出：Python, Java
        }
        /**
         *  2. 作为队列（Queue）使用（FIFO 先进先出）
         * */
        Deque<String> queue = new ArrayDeque<>();

        // 入队操作（尾部添加）
        queue.offer("Apple");
        queue.offer("Banana");

        // 出队操作（头部删除）
        while (!queue.isEmpty()) {
            System.out.println(queue.poll()); // 输出：Apple, Banana
        }

        /**
         *  3.作为双端队列（Deque）使用
         * */
        Deque<String> deque = new ArrayDeque<>();

        // 两端操作
        deque.addFirst("Head");
        deque.addLast("Tail");

        // 获取但不删除
        System.out.println(deque.peekFirst()); // Head
        System.out.println(deque.peekLast());  // Tail
    }

}
