package com.xhl.Career_Growth.ODExam.TestPaperA;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-25 22:01
 * @Description:  计算最接近的数
 */
public class FindClosetNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();

        int i = line.lastIndexOf(",");

        int[] x =
                Arrays.stream(line.substring(1, i - 1).split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(line.substring(i + 1));

        System.out.println(getResult(x, k));
    }

    public static int getResult(int[] x, int k) {
        int n = x.length;

        // x数组的中位数
        int mid = Arrays.stream(x).sorted().toArray()[n / 2];

        // 初始化滑窗0~k-1, window为滑窗内部元素的表达式计算结果
        int window = x[0];
        for (int i = 1; i < k; i++) {
            window -= x[i];
        }

        // window和中位数的差距
        int minDiff = Math.abs(mid - window);
        // window滑窗起始索引
        int idx = 0;

        // 滑窗右移
        for (int i = 1; i <= n - k; i++) {
            // 右移一格后，新滑窗的表达式计算结果
            window += -x[i - 1] + 2 * x[i] - x[i + k - 1];

            // 新滑窗window值和中位数的差距
            int diff = Math.abs(mid - window);

            // 结果最接近于数组中位数的下标 i ，如果有多个 i 满足条件，请返回最大的 i
            if (diff <= minDiff) {
                minDiff = diff;
                idx = i;
            }
        }

        return idx;
    }
}
