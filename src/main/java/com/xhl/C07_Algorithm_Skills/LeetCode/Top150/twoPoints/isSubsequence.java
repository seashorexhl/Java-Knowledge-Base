package com.xhl.C07_Algorithm_Skills.LeetCode.Top150.twoPoints;

/**
 * @Author: xhl
 * @Date: 2026-04-17 10:38
 * @Description: isSubsequence 是否为子序列
 */
public class isSubsequence {
    public static void main(String[] args) {
        String s = "abc", t = "ahbgdc";
        isSubsequence is = new isSubsequence();

        boolean b = is.isSubsequence(s, t);
        System.out.println("s 是否是t的子序列？" + b);
    }

    /**
     * 方法一：双指针
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == n;
    }

    /**
     * 方法二：动态规划
     */
    public boolean isSubsequence1(String s, String t) {
        int n = s.length(), m = t.length();

        // 表示字符串 t 中从位置 i 开始往后字符 j 第一次出现的位置
        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }
        // 状态转移 方程
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
