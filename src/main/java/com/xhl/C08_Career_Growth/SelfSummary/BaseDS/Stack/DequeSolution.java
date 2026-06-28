package com.xhl.Career_Growth.SelfSummary.BaseDS.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-06-11 06:08
 * @Description:  Java Deque 使用模板  基于数组的双端队列
 */
public class DequeSolution {
    public static void main(String[] args) {
        /**
         * 1. 核心初始化
         * */
        Deque<Integer> deque = new ArrayDeque<>();

        /**
         * 2. 场景一：作为普通队列使用 (FIFO - 尾进头出)
         * 适用于层序遍历、消息消费等场景。
         * */
        // 入队：添加到尾部
        deque.offerLast(1); // 或 deque.offer(1)

        // 查看队首元素：不移除
                Integer head = deque.peekFirst(); // 或 deque.peek()

        // 出队：从头部移除并返回
        Integer removed = deque.pollFirst(); // 或 deque.poll()
        /**
         * 3.场景二：作为栈使用 (LIFO - 头进头出)
         * 适用于前/中/后序非递归遍历、撤销功能等场景。
         * */
        // 入栈：压入到头部
        deque.push(1); // 等价于 offerFirst(1)

        // 查看栈顶元素
        Integer top = deque.peekFirst(); // 或 deque.peek()

        // 出栈：从头部弹出
        Integer popped = deque.pop(); // 等价于 pollFirst()
        /**
         * 4. 场景三：真正的双端操作 (两端自由增删)
         * */
        // 头部添加 / 尾部添加
        deque.offerFirst(0);
        deque.offerLast(2);

        // 头部移除 / 尾部移除
        deque.pollFirst();
        deque.pollLast();

        /* stack.push(root); // push 对应 addFirst
        stack.pop();      // pop 对应 removeFirst*/
    }

}
