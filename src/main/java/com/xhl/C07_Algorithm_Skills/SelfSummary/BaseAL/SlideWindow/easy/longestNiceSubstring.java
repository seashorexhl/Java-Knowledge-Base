package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow.easy;

/**
 * @Author: xhl
 * @Date: 2026-07-07 01:07
 * @Description:   1763. 最长的美好子字符串
 *  定义：字符串中每种出现过的字母，其大写和小写形式必须同时存在。
 *  1.枚举 + 位运算
 *  2.滑动窗口
 */
public class longestNiceSubstring {
    // 方法一（枚举法）使用的成员变量：记录最长美好子串的起始位置和长度
    private int maxPos;
    private int maxLen;
    // 方法二（滑动窗口法）使用的成员变量：记录最长美好子串的起始位置和长度
    private int maxPos1;
    private int maxLen1;
    // 测试入口
    static void main() {
        String s = "YazaAay";
        longestNiceSubstring lns = new longestNiceSubstring();
        String s1 = lns.longestNiceSubstring(s);
        System.out.println("最长的美好子字符串：" + s1);
    }
    /**
     * 方法一：枚举法（暴力法）
     * 思路：双层 for 循环遍历所有可能的子字符串。
     *       利用位运算（bitmask）极其高效地记录当前窗口内出现过的小写和大写字母。
     *       如果 lower == upper，说明大小写都成对出现了，是一个美好子串，更新最大长度。
     * 复杂度：时间 O(n^2)，空间 O(1)
     */
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int maxPos = 0;
        int maxLen = 0;
        // 外层循环：枚举子串的起始位置

        for (int i = 0; i < n; ++i) {
            int lower = 0;// 位掩码，记录当前窗口内出现过的小写字母
            int upper = 0;// 位掩码，记录当前窗口内出现过的大写字母
            // 内层循环：枚举子串的结束位置，逐步扩大窗口

            for (int j = i; j < n; ++j) {
                if (Character.isLowerCase(s.charAt(j))) {
                    // 将对应的小写字母位置置为 1（例如 'a' 对应第 0 位，'b' 对应第 1 位）

                    lower |= 1 << (s.charAt(j) - 'a');
                } else {
                    // 将对应的大写字母位置置为 1

                    upper |= 1 << (s.charAt(j) - 'A');
                }
                // 如果当前窗口内小写字母集合和大写字母集合完全相同，说明是美好子串

                if (lower == upper && j - i + 1 > maxLen) {
                    maxPos = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(maxPos, maxPos + maxLen);
    }
    /**
     * 方法二：分治法（Divide and Conquer）
     * 思路：如果一个字符串是美好的，那么它包含的所有字母大小写必须同时存在。
     *       如果某个字母只有大写没有小写（或反之），它绝对不可能出现在最终的美好子串中。
     *       因此，用这些"不合法"的字母作为分割点，将字符串劈开，对左右两边分别递归。
     * 复杂度：时间 O(n log n) ~ O(n^2)，空间 O(n)（递归栈深度）
     */
    public String longestNiceSubstring1(String s) {
        this.maxPos = 0;
        this.maxLen = 0;
        dfs(s, 0, s.length() - 1);
        return s.substring(maxPos, maxPos + maxLen);
    }
    /**
     * 分治递归函数：在 s[start..end] 范围内寻找最长美好子串
     */
    public void dfs(String s, int start, int end) {
        if (start >= end) {
            return;
        }
        // 统计当前区间内出现过的小写和大写字母（位掩码）

        int lower = 0, upper = 0;
        for (int i = start; i <= end; ++i) {
            if (Character.isLowerCase(s.charAt(i))) {
                lower |= 1 << (s.charAt(i) - 'a');
            } else {
                upper |= 1 << (s.charAt(i) - 'A');
            }
        }
        // 如果当前整个区间已经是美好子串，直接更新全局最大值并返回

        if (lower == upper) {
            if (end - start + 1 > maxLen) {
                maxPos = start;
                maxLen = end - start + 1;
            }
            return;
        }
        // valid：大小写同时存在的字母掩码（即"合法"字母）
        // 只有 lower & upper 为 1 的位，才代表该字母大小写都出现过
        int valid = lower & upper;
        // 用"不合法"的字母作为分割点，将当前区间拆分成多个子区间，分别递归

        int pos = start;
        while (pos <= end) {
            start = pos;
            // 用"不合法"的字母作为分割点，将当前区间拆分成多个子区间，分别递归

            while (pos <= end && (valid & (1 << Character.toLowerCase(s.charAt(pos)) - 'a')) != 0) {
                ++pos;
            }
            // 对找到的合法连续子区间进行递归

            dfs(s, start, pos - 1);
            // pos 指向的是"不合法"字母，跳过它，继续寻找下一个合法区间

            ++pos;
        }
    }
    /**
     * 方法三：滑动窗口（进阶最优解）
     * 核心难点：普通滑动窗口要求"窗口扩大时条件变好，缩小时条件变坏"（单调性），
     *          但本题中，加入一个字符可能让原本美好的串变得不美好，不满足单调性。
     * 破局思路：既然不满足单调性，就人为制造单调性。字母最多只有 26 个，
     *          我们可以枚举窗口内包含的"字母种类数"（typeNum）。
     *          当规定"窗口内恰好包含 typeNum 种字母"时，窗口就变成了标准的滑动窗口！
     * 复杂度：时间 O(26 * n) = O(n)，空间 O(1)
     */

    public String longestNiceSubstring2(String s) {
        this.maxPos1 = 0;
        this.maxLen1 = 0;
        // 统计字符串中一共有多少种不同的字母（不区分大小写）

        int types = 0;
        for (int i = 0; i < s.length(); ++i) {
            types |= 1 << (Character.toLowerCase(s.charAt(i)) - 'a');
        }
        types = Integer.bitCount(types);// 计算位掩码中 1 的个数，即字母种类数
        // 枚举窗口内允许的字母种类数（从 1 种到 types 种）
        // 每次枚举，都将问题转化为"恰好包含 i 种字母的最长美好子串"
        for (int i = 1; i <= types; ++i) {
            check(s, i);
        }
        return s.substring(maxPos1, maxPos1 + maxLen1);
    }
    /**
     * 滑动窗口检查函数：在 s 中寻找"恰好包含 typeNum 种字母"的最长美好子串
     * @param s        原始字符串
     * @param typeNum  窗口内允许的字母种类数
     */
    public void check(String s, int typeNum) {
        int[] lowerCnt = new int[26];// 记录窗口内每种小写字母的出现次数
        int[] upperCnt = new int[26];// 记录窗口内每种大写字母的出现次数
        int cnt = 0;// 记录窗口内"大小写成对出现"的字母种类数
        // 滑动窗口：l 为左指针，r 为右指针

        for (int l = 0, r = 0, total = 0; r < s.length(); ++r) {
            int idx = Character.toLowerCase(s.charAt(r)) - 'a';
            // 右指针向右扩展，将 s[r] 纳入窗口
            if (Character.isLowerCase(s.charAt(r))) {
                ++lowerCnt[idx];
                // 如果这个小写字母是第一次出现，且对应的大写字母已经存在，说明新凑成一对

                if (lowerCnt[idx] == 1 && upperCnt[idx] > 0) {
                    ++cnt;
                }
            } else {
                ++upperCnt[idx];
                // 如果这个大写字母是第一次出现，且对应的小写字母已经存在，说明新凑成一对

                if (upperCnt[idx] == 1 && lowerCnt[idx] > 0) {
                    ++cnt;
                }
            }
            // 更新窗口内的字母种类数
            // 如果该字母是第一次进入窗口（大小写总次数为 1），种类数 +1
            total += (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
            // 如果窗口内字母种类数超过了限制，移动左指针缩小窗口
            while (total > typeNum) {
                idx = Character.toLowerCase(s.charAt(l)) - 'a';
                // 如果该字母即将完全移出窗口（移出后总次数变为 0），种类数 -1

                total -= (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
                if (Character.isLowerCase(s.charAt(l))) {
                    --lowerCnt[idx];
                    // 如果小写字母移除后变为 0，但大写字母还在，说明丢失了一对

                    if (lowerCnt[idx] == 0 && upperCnt[idx] > 0) {
                        --cnt;
                    }
                } else {
                    --upperCnt[idx];
                    // 如果大写字母移除后变为 0，但小写字母还在，说明丢失了一对

                    if (upperCnt[idx] == 0 && lowerCnt[idx] > 0) {
                        --cnt;
                    }
                }
                ++l;
            }
            // 如果窗口内"大小写成对出现"的字母种类数 == 允许的字母种类数
            // 说明窗口内所有字母都大小写成对，是一个美好子串，更新全局最大值

            if (cnt == typeNum && r - l + 1 > maxLen) {
                maxPos = l;
                maxLen = r - l + 1;
            }
        }
    }


}
