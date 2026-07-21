package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.String.easy;

/**
 * @Author: xhl
 * @Date: 2026-06-21 15:28
 * @Description: strStr 28. 找出字符串中第一个匹配项的下标 字符串匹配
 */
public class firstStr {
    static void main() {
        String haystack = "sasdbutsad", needle = "sad";
        firstStr fs = new firstStr();
        int i = fs.strStr(haystack, needle);
        System.out.println("字符串中第一个匹配项的下标 暴力匹配: " + i);

        int res = fs.strStr1(haystack, needle);
        System.out.println("KMP算法：" + res);


    }
    /**
     *  方法一：暴力匹配
     * */
    public int strStr(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();

        for (int i = 0; i +n <= m; i++) {
            boolean flag = true;
            for (int j=0;j<n; j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
    /**
     *  方法二：Knuth-Morris-Pratt 算法 ：KMP算法
     * */
    public int strStr1(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
}
