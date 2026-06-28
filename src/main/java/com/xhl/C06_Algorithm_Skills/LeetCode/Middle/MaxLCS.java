package com.xhl.C06_Algorithm_Skills.LeetCode.Middle;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-05 04:27
 * @Description: 最长的 连续相同子字符串 longest contiguous substring of identical characters
 */
public class MaxLCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();

        MaxLCS mlcs = new MaxLCS();
        System.out.println("最长的连续子串："+mlcs);

    }
    public static int maxLCS(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int[][] dp = new int[lenA + 1][lenB + 1];
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[lenA][lenB];
    }
}
