package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.String.middle;

/**
 * @Author: xhl
 * @Date: 2026-06-21 16:06
 * @Description: 找出 字符串中 第N 个匹配想项的下标
 */
public class strStrNth {
    static void main() {
        String haystack = "abssabssabab", needle = "ab";
        int n = 3;
        strStrNth ssn = new strStrNth();
        System.out.println(ssn.strStrNth(haystack, needle, n));
    }
    /**
     * 核心思路：
     * 增加一个计数器 count，初始值为 0。
     * 每次匹配成功时，将 count 加 1。
     * 当 count == N 时，说明当前匹配到的就是第 N 次出现的位置，直接返回当前的索引 i。
     * 如果遍历完整个字符串后 count 仍未达到 N，则返回 -1。
     * */
    public int strStrNth(String haystack, String needle, int n) {
        int m = haystack.length(), len = needle.length();

        // 边界条件：如果 needle 为空，按惯例返回 0
        if (len == 0) return 0;

        // 边界条件：如果 n 小于等于 0，属于无效输入
        if (n <= 0) return -1;

        int count = 0; // 记录匹配成功的次数

        for (int i = 0; i + len <= m; i++) {
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++; // 匹配成功，计数加 1
                if (count == n) {
                    return i; // 达到目标次数，直接返回当前位置
                }
            }
        }

        // 遍历结束仍未找到第 n 次出现的位置
        return -1;
    }
}
