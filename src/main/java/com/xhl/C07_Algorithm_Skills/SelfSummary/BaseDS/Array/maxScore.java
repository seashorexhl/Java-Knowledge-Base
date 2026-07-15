package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Array;

/**
 * @Author: xhl
 * @Date: 2026-06-21 07:22
 * @Description: 分割字符串的最大得分
 */
public class maxScore {
    static void main() {
        String s = "011101";
        maxScore ms = new maxScore();
        System.out.println("分割字符串的最大得分为：s"+ms.maxScore(s));
    }
    //方法一：枚举每个分割点 循环+计数
    public int maxScore(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 1; i < n; i++) {
            int score = 0;
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == '0') {
                    score++;
                }
            }
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    score++;
                }
            }
            ans = Math.max(ans, score);
        }
        return ans;
    }
    // 方法二： 两次遍历
    public int maxScore1(String s) {
        int score = 0;
        int n = s.length();
        if (s.charAt(0) == '0') {
            score++;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1') {
                score++;
            }
        }
        int ans = score;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                score++;
            } else {
                score--;
            }
            ans = Math.max(ans, score);
        }
        return ans;
    }
}
