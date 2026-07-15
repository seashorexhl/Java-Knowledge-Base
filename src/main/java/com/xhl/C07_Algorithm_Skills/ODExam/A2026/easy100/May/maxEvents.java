package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-10 01:06
 * @Description: 最大化游戏试玩资格分发-100分
 */
public class maxEvents {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                intervals[i][j] = sc.nextInt();
            }
        }
        maxEvents me = new maxEvents();
        System.out.println("最多能安排多少场试玩？");
        System.out.println(me.maxEvents(intervals));
    }
    /**
     *  贪心算法
     * */
        /*按结束时间升序排序所有活动
    选择结束时间最早的活动
    从剩余活动中选择第一个开始时间≥上次选择结束时间的活动
    重复步骤3直到遍历完所有活动*/
    public int maxEvents(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 0, lastEnd = -1;
        for (int[] itv : intervals) {
            if (itv[0] >= lastEnd) {
                count++;
                lastEnd = itv[1];
            }
        }
        return count;
    }
}
