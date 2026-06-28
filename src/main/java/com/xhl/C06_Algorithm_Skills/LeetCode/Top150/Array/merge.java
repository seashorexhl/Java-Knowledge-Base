package com.xhl.Algorithm_Skills.LeetCode.Top150.Array;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-04-17 10:21
 * @Description: 合并两个有序数组  双指针
 */
public class merge {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        merge mer = new merge();
        mer.merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }
    // 方法一 直接 在 nums1 数字后面添加 nums2
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i+m] = nums2[i];
        }
        Arrays.sort(nums1);
    }
    // 方法二、双指针
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0; //
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }
    // 方法三 逆向双指针
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}
