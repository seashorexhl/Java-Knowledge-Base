package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Math;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-25 19:33
 * @Description: 66. 加一
 *
 */
public class plusOne {
    static void main() {
        int[] digits = {9,9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    /**
     *    方法一：找出最长的后缀 9
    */
    /**
     * 算法
     * 我们只需要对数组 digits 进行一次逆序遍历，找出第一个不为 9 的元素，将其加一并将后续所有
     * 元素置零即可。如果 digits 中所有的元素均为 9，那么对应着「思路」部分的第三种情况，
     * 我们需要返回一个新的数组。
     * */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];

                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }
        // digits 中所有的元素均为 9
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }
}
