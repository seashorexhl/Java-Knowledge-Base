package com.xhl.Career_Growth.SelfSummary.BaseDS.Queue.Deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xhl
 * @Date: 2026-06-15 05:19
 * @Description: 双端队列 Deque
 *
 */
public class DequeLifeStyle {

    static void main() {

        /*一、Deque 生命周期*/
        Deque<String> deque = new ArrayDeque<>();  // 基于动态数组，效率高
        Deque<Integer> deque2 = new LinkedList<>(); // 基于双向链表，支持 null 元素

        /**
         * 二、使用阶段
         * 动态操作：根据业务需求在队头/队尾执行插入、删除或访问。
         * 典型场景：
         * 作为栈：仅使用 push()（队头插入）和 pop()（队头删除）。
         * 作为队列：使用 offer()（队尾插入）和 poll()（队头删除）。
         * 作为双端队列：混合使用队头/队尾操作（如滑动窗口算法）29。
         * */

        /**
         * 三、 销毁阶段
         * 调用 clear() 可立即移除所有元素，加速资源回收。
         * */
        deque.clear(); // 清空队列，元素引用失效

        /**
         * 常用API:
         * */

        // 作为栈使用（LIFO）
        deque.push("A");
        deque.push("B");
        System.out.println(deque.pop()); // 输出 B

        // 作为队列使用（FIFO）
        deque.offer("X");
        deque.offer("Y");
        System.out.println(deque.poll()); // 输出 X

        // 双端操作（滑动窗口场景）
        deque.offerLast("C");
        deque.offerFirst("D");
        System.out.println(deque.peekFirst()); // 输出 D
        System.out.println(deque.peekLast());  // 输出 C

        /**
         *  实现 滑动窗口最大值
         * */
        DequeLifeStyle dls = new DequeLifeStyle();
        int[]  nums = {2,3,4,5,6,8};
        int k = 3;
        int[] ints = dls.maxSlidingWindow(nums, k);
        System.out.println("滑动窗口最大值：");
        System.out.println(Arrays.toString(ints));
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] res = new int[n - k + 1];

        // 存储索引，保持队列中对应元素的值单调递减
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 1. 队尾淘汰：移除队列中所有小于当前元素的索引
            // 因为它们既没有当前元素大，又比当前元素更早离开窗口
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // 2. 将当前元素的索引入队
            deque.offerLast(i);

            // 3. 队首淘汰：如果队首索引已经滑出当前窗口范围，则将其移除
            // 当前窗口的左边界为 i - k + 1
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 4. 记录最大值：当窗口大小达到 k 时，队首即为当前窗口的最大值
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
