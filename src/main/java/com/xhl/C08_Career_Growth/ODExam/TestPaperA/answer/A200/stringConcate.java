package com.xhl.C08_Career_Growth.ODExam.TestPaperA.answer.A200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-04 20:40
 * @Description: 06 stringConcat 字符串拼接
 */
public class stringConcate {
    static String s;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.next();
        n = sc.nextInt();

        System.out.println(getResult());
    }

    public static int getResult() {
        if (s.length() < n) {
            // 无法拼接出满足条件的字符串
            return 0;
        }

        char[] cArr = s.toCharArray();

        for (char c : cArr) {
            // 输入非法
            if (c < 'a' || c > 'z') return 0;
        }

        // 排序cArr，可以方便后面求解全排列时，进行树层去重
        Arrays.sort(cArr);
        return dfs(cArr, -1, 0, new boolean[cArr.length], 0);
    }

    /**
     * 全排列求解
     *
     * @param cArr 基于cArr数组求解全排列
     * @param pre 排列最后一个字符在cArr中的位置
     * @param level 排列的长度
     * @param used used[i] 用于标记 cArr[i] 元素是否已使用
     * @param count 符号要求的排列有几个
     * @return count
     */
    public static int dfs(char[] cArr, int pre, int level, boolean[] used, int count) {
        // 当排列长度到达n，则是一个符合要求的排列
        if (level == n) {
            // 符合要求的排列个数+1
            return ++count;
        }

        for (int i = 0; i < cArr.length; i++) {
            // 每个字符只能用一次
            if (used[i]) continue;

            // 相同的字符不能相邻， pre指向前面一个被选择的字符的在cArr中的位置，i指向当前被选择的字符在cArr中的位置
            if (pre >= 0 && cArr[i] == cArr[pre]) continue;

            // 树层去重(去除重复排列)
            if (i > 0 && cArr[i] == cArr[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            count = dfs(cArr, i, level + 1, used, count);
            used[i] = false;
        }

        return count;
    }
}
