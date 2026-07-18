package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:32
 * @Description: 物流仓库货物调配优化-100分
 * 贪心策略 + 优先队列（堆）
 * 计算在不超过k次操作的情况下，仓库处理完所有货物的最短总处理时间。
 */
public class minProcessingTime {
    static void main(String[] args) {
        int[] nums = {5,3,4,3};
        int k = 3;
        int i = minProcessingTime(nums, k);
        System.out.println("计算在不超过k次操作的情况下，仓库处理完所有货物的最短总处理时间:"+ i);
    }

    /**
     * 核心方法：计算在最多k次操作下的最短总处理时间
     * 核心贪心策略：每次操作优先选择【当前最长的连续段】进行减1操作，这样能让总和下降最快。
     */
    public static int minProcessingTime(int[] nums, int k) {
        int n = nums.length;
        // 复制一份数组用于模拟操作，避免修改原数组

        int[] b = Arrays.copyOf(nums, n);
        // 1. 计算初始的总处理时间

        long S = 0;
        for (int num : nums) S += num;

        // 2. 找出初始的所有连续段（以0为分界线）

        List<Segment> segments = new ArrayList<>();
        int i = 0;
        while (i < n) {
            // 跳过处理时间为0的货物（它们会打断连续性）
            if (b[i] == 0) {
                i++;
                continue;
            }
            // 找到一个大于0的起点，向后延伸直到遇到0或数组结尾
            int start = i;
            while (i < n && b[i] > 0) i++;
            int end = i - 1;
            int len = end - start + 1;
            // 将找到的连续段加入列表
            segments.add(new Segment(len, start, end));
        }

        // 3. 构建最大堆（优先队列），按连续段的长度降序排列
        // 目的：每次都能以 O(log N) 的时间复杂度取出当前最长的连续段
        PriorityQueue<Segment> heap = new PriorityQueue<>((a, b1) -> b1.len - a.len);
        for (Segment seg : segments) heap.add(seg);

        long totalReduction = 0;// 记录总共减少了多少处理时间
        int count = 0;// 记录已经执行的操作次数
        // 4. 核心贪心循环：在不超过k次操作的前提下，不断削最长的段

        while (count < k && !heap.isEmpty()) {
            // 取出当前最长的连续段

            Segment seg = heap.poll();
            int len = seg.len;
            int start = seg.start;
            int end = seg.end;

            // 模拟操作：将该连续段内所有货物的处理时间减1
            for (int i1 = start; i1 <= end; i1++) {
                b[i1]--;
            }
            // 因为段内每个元素都减了1，所以总时间减少了 len

            totalReduction += len;

            // 检查该段内是否有元素减到了0（0会打断连续性，导致段分裂）
            List<Integer> zeroPositions = new ArrayList<>();
            for (int i2 = start; i2 <= end; i2++) {
                if (b[i2] == 0) {
                    zeroPositions.add(i2); // 记录变成0的位置
                }
            }
            // 如果没有元素变成0，说明段没有断裂，长度减1后放回堆中
            if (zeroPositions.isEmpty()) {
                // 放回
                heap.add(new Segment(len, start, end));
            } else {
                // 如果有元素变成了0，需要把原来的段“切开”成更小的子段
                Collections.sort(zeroPositions); // 确保0的位置是有序的
                List<Segment> subSegments = new ArrayList<>();
                int prev = start;// 记录上一个切割点
                // 遍历所有变成0的位置，按0的位置切割出非0的子段
                for (int pos : zeroPositions) {
                    // 如果 prev 到 pos-1 之间有元素，说明这是一个有效的子段

                    if (prev <= pos - 1) {
                        int subStart = prev;
                        int subEnd = pos - 1;
                        int subLen = subEnd - subStart + 1;
                        if (subLen > 0) {
                            subSegments.add(new Segment(subLen, subStart, subEnd));
                        }
                    }
                    prev = pos + 1; // 更新切割点，跳过当前的0
                }
                // 处理最后一个0之后到段末尾可能存在的子段
                if (prev <= end) {
                    int subStart = prev;
                    int subEnd = end;
                    int subLen = subEnd - subStart + 1;
                    if (subLen > 0) {
                        subSegments.add(new Segment(subLen, subStart, subEnd));
                    }
                }
                // 将所有切出来的新子段放回最大堆中，参与后续的比较
                for (Segment sub : subSegments) {
                    heap.add(sub);
                }
            }

            count++; // 操作次数加1
        }
        // 5. 最终结果 = 初始总时间 - 累计减少的总时间
        return (int) (S - totalReduction);
    }

    // 内部类：表示一个连续的货物处理段
    static class Segment {
        int len;  // 该连续段的长度（决定了每次操作能减少的总时间）
        int start;  // 该连续段在原数组中的起始索引
        int end;  // 该连续段在原数组中的结束索引
        public Segment(int len, int start, int end) {
            this.len = len;
            this.start = start;
            this.end = end;
        }
    }
}
