package com.xhl.C08_Career_Growth.ODExam.A177.Answer;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-11 14:08
 * @Description: 043 用户调度问题
 */
public class TaskScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] res = new int[n][3];
        for (int i = 0; i < n; i++) {
            res[i][0] = sc.nextInt();
            res[i][1] = sc.nextInt();
            res[i][2] = sc.nextInt();
        }

        System.out.println(getResult(n, res));
    }
    /*  开始计算  */
    public static int getResult(int n, int[][] res) {
        int last = -1;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            last = getMinEleIdx(res[i], last);
            sum += res[i][last];
        }

        return sum;
    }
    /*  根据规则 挑选  */
    public static int getMinEleIdx(int[] arr, int excludeIdx) {
        int minEleVal = Integer.MAX_VALUE;
        int minEleIdx = -1;

        for (int i = 0; i < arr.length; i++) {
            if (i == excludeIdx) continue;

            if (arr[i] <= minEleVal) {
                minEleVal = arr[i];
                minEleIdx = i;
            }
        }
        return minEleIdx;
    }
}
