package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Sort;

/**
 * @Author: xhl
 * @Date: 2026-07-08 16:29
 * @Description: 希尔排序 ShellSort
 *  将待排序数组按下标的一定增量（Gap）分组，对每组使用直接插入排序算法排序；
 *  随着增量逐渐减少，每组包含的元素越来越多，当增量减至 1 时，整个数组恰好被分成一组，
 *  此时进行最后一次插入排序，数组便完全有序。
 *  缓存不友好 上限不够高
 */
public class ShellSort {
    static void main() {

    }
    // 分组 + 插入排序
    public void shellSort(int[] arr) {
        int n = arr.length;

        // 1. 确定初始增量，并逐渐缩小 (n/2, n/4 ... 1)
        for (int gap = n / 2; gap > 0; gap /= 2) {

            // 2. 对每个分组进行插入排序
            // 这里巧妙地将“分组”和“插入”结合在了一起
            // 从第 gap 个元素开始，依次向后遍历
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;

                // 3. 在组内向前比较并移动（步长为 gap）
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
