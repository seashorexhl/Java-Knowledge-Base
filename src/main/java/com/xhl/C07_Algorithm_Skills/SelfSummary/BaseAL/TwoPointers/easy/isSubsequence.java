package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.TwoPointers.easy;

/**
 * @Author: xhl
 * @Date: 2026-04-17 10:38
 * @Description: 392. 判断子序列 isSubsequence
 *  双指针 + 动态规划
 */
public class isSubsequence {
    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        isSubsequence is = new isSubsequence();

        boolean b = is.isSubsequence(s, t);
        System.out.println("是否是 子序列？" + b);
    }

    /**
     *  方法一：双指针
     * */
    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (m < n) {
            return false;
        }
        int left = 0, right = 0;

        while (left < n && right < m) {
            if (s.charAt(left) == t.charAt(right)) {
                left++;
            }
            right++;
        }
        return left == n;
    }

    /**
     *  方法二：动态规划
     * */
    public boolean isSubsequence1(String s, String t) {
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;

    }
}