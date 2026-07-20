package com.xhl.C10_Career_Growth.ODExam.TestPaperA.answer.A200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-04 20:37
 * @Description: 04 horse racing strategy 田忌赛马
 *
 */
public class horseRacingStrategy {
    static int[] a;
    static int[] b;

    static int maxBiggerCount = 0;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 求解a的全排列
        dfs(0, new boolean[a.length], 0);

        System.out.println(ans);
    }

    public static void dfs(int level, boolean[] used, int biggerCount) {
        if (level >= a.length) {
            if (biggerCount > maxBiggerCount) {
                maxBiggerCount = biggerCount;
                ans = 1;
            } else if (biggerCount == maxBiggerCount) {
                ans++;
            }

            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            // biggerCount记录当前全排列中a[level] > b[level]的位置的数量, 此时a[level] == a[i]
            dfs(level + 1, used, biggerCount + (a[i] > b[level] ? 1 : 0));
            used[i] = false;
        }
    }


}
