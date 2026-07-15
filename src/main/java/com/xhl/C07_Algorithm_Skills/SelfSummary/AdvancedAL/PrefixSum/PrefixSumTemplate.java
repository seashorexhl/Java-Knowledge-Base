package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.PrefixSum;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-23 09:34
 * @Description:  前缀和 模板 区间
 */
public class PrefixSumTemplate {

    static void main() {
        // 1. 构建前缀和数组（下标从1开始，避免边界判断）
        int[] nums = {1, 2, 3, 4, 5};
        int n = nums.length;
        int[] pre = new int[n + 1]; // pre[0] = 0
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        // 2. 查询区间 [l, r]（0-based 原数组下标）的和
        int l = 1, r = 3; // 对应 nums[1]~nums[3] = 2+3+4=9
        int sum = pre[r + 1] - pre[l];
        // 等价于 pre[R] - pre[L-1]，其中 R=r+1, L=l
        System.out.printf("一维前缀和为：" + sum);
    }

    /**
     * 1. 前缀和 + 哈希表（求子数组个数/最长子数组）
     **/
    // 例题：和为 K 的子数组个数（LeetCode 560）
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 前缀和为0出现1次
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            // 查找是否存在前缀和 = sum - k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    /**
     * 2. 二维前缀和（矩阵区域和）
     *
     */
    // 构建二维前缀和
    public int subarraySum1(int[] nums, int k) {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = matrix[i - 1][j - 1]
                        + pre[i - 1][j]
                        + pre[i][j - 1]
                        - pre[i - 1][j - 1];
            }
        }

        // 查询子矩阵 [row1..row2][col1..col2]（0-based）
        int row1 = 1, col1 = 1, row2 = 2, col2 = 2;
        int sum = pre[row2 + 1][col2 + 1]
                - pre[row1][col2 + 1]
                - pre[row2 + 1][col1]
                + pre[row1][col1];
        return sum;
    }

    /**
     * 3. 前缀和 + 单调队列（滑动窗口最值）
     *  适用场景：求固定长度窗口和的最值，或前缀和差值最值。
     */
    public int subarraySum() {
        // 例题：长度至少为 k 的最大子数组平均值（可变形）
        // 这里给你一个通用框架：滑动窗口内前缀和的极值
        int[] nums = {2,4,65,89,123};
        int n = nums.length;
        int k = 3;
        int[] pre = new int[n+1];
        // ... 构建前缀和
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            // 维护单调队列（根据需求递增或递减）
            while (!deque.isEmpty() && pre[deque.peekLast()] >= pre[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 移除过期下标
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            // 此时队首即窗口内最优前缀和位置
        }
        return pre[n];
    }
     /**
      * 四、快速默写模板（考试前背下来）
      * */
    // 一维模板（带哈希）
    public int solve(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;
        for (int x : nums) {
            sum += x;
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }


}
