package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.C0719;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-21 11:46
 * @Description: 2026-07-19 小明的顺风车
 *  动态规划 + 二分查找 + 排序
 */
public class maxRevenue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\\A");
        String input = sc.hasNext() ? sc.next() : "";
        List<Integer> nums = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                cur.append(ch);
            } else if (cur.length() > 0) {
                nums.add(Integer.parseInt(cur.toString()));
                cur.setLength(0);
            }
        }
        if (cur.length() > 0) {
            nums.add(Integer.parseInt(cur.toString()));
        }

        int n = nums.isEmpty() ? 0 : nums.get(0);
        List<Ride> rides = new ArrayList<>();
        for (int i = 1; i + 1 < nums.size(); i += 2) {
            rides.add(new Ride(nums.get(i), nums.get(i + 1)));
        }
        System.out.println(solve(n, rides));
    }

    static int solve(int n, List<Ride> rides) {
        // 按终点升序排序，保证 dp 转移只依赖已经处理过的乘客
        Collections.sort(rides, Comparator.comparingInt(a -> a.end));
        int m = rides.size();
        int[] ends = new int[m];
        for (int i = 0; i < m; i++) {
            ends[i] = rides.get(i).end;
        }

        // dp[i] 表示只考虑前 i 个乘客时的最大收益
        int[] dp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            Ride cur = rides.get(i - 1);
            int profit = cur.end - cur.start;

            // 二分查找最后一个终点 <= 当前起点的乘客数量
            int left = 0, right = i - 2, count = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (ends[mid] <= cur.start) {
                    count = mid + 1;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // 当前乘客可选可不选，取两种方案的最大值
            dp[i] = Math.max(dp[i - 1], dp[count] + profit);
        }
        return dp[m];
    }

    /**
     *  动态规划
     * */
    static class Ride {
        int start;
        int end;

        Ride(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}