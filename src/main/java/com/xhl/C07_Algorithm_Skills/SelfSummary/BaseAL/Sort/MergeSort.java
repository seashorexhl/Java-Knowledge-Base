package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Sort;

/**
 * @Author: xhl
 * @Date: 2026-06-09 00:16
 * @Description: 5.归并排序
 *
 */
public class MergeSort {
    static void main() {

    }
    // 归并排序
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // 防溢出
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        // 双指针合并
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        // 处理剩余元素
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // 拷回原数组
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
