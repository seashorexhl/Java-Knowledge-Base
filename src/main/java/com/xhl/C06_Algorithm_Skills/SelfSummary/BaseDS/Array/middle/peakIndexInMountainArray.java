package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Array.middle;

/**
 * @Author: xhl
 * @Date: 2026-06-21 08:28
 * @Description: 852. 山脉数组的峰顶索引
 * 给定一个长度为 n 的整数 山脉 数组 arr ，其中的值递增到一个 峰值元素 然后递减。
 * 返回峰值元素的下标。
 * 你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
 */
public class peakIndexInMountainArray {
    static void main() {
        int[] arr ={1,5,7,4,3};
        peakIndexInMountainArray pa=new peakIndexInMountainArray();
        System.out.println(pa.peakIndexInMountainArray(arr));
    }
    // 方法一：   枚举
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int ans = -1;
        for (int i = 1; i < n - 1; ++i) {
            if (arr[i] > arr[i + 1]) {
                ans = i;
                break;
            }
        }
        return ans;
    }
    // 方法二： 二分查找
    public int peakIndexInMountainArray1(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
