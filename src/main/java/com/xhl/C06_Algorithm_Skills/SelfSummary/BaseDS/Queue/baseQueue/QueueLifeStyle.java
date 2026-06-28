package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Queue.baseQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-03 04:12
 * @Description: 队列 生命周期 和 常用 API
 */
public class QueueLifeStyle {
    public static void main(String[] args) {
        // 新建一个 存储 String的队列
        Queue<Integer> queue = new LinkedList<>();

        // 增删改查
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        // 返回队头的元素
        Integer peek = queue.peek();
        System.out.println(peek);
        // 删除并返回队头的元素
        System.out.println(queue.poll());
        /*常用 API*/
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());


    }
}
