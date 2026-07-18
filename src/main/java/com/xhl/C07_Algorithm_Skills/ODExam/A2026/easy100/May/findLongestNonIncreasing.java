package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:50
 * @Description:    美观的灯笼-100分
 *  数组遍历 + 滑动窗口
 */
/**
 * 输入
 * N个正整数M（1≤M≤100），表示每个挂灯点所挂的灯笼尺寸。
 * 输出
 * 输出两个整数：第一个是符合题意的灯笼数，第二个是开始挂灯笼的挂灯点位置（从0开始计数)
 * */
public class findLongestNonIncreasing {

    static void main(String[] args) {
        int[] arr = {};
        findLongestNonIncreasing(arr);

    }
    // 数组遍历 + 滑动窗口
    public static void findLongestNonIncreasing(int[] arr) {
        int maxLen = 0;
        int start = 0;
        int currentLen = 1;
        int currentStart = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) {
                currentLen++;
            } else {
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    start = currentStart;
                }
                currentLen = 1;
                currentStart = i;
            }
        }

        if (currentLen > maxLen) {
            maxLen = currentLen;
            start = currentStart;
        }
        System.out.println(maxLen + " " + start);

    }
}