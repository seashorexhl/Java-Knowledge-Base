package com.xhl.C09_Career_Growth.ODExam.A177.Answer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-05 16:48
 * @Description: 03 补种未成活胡杨 ReplantPoplarEuphratica 最长的连续序列
 */
public class ReplantPoplarEuphratica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 创建一个扫描器对象，用于读取输入
        Scanner input = new Scanner(System.in);
        // 读取总共的胡杨树数量
        int total = sc.nextInt();
        // 读取未成活的胡杨树数量
        int deadCount  = sc.nextInt();
        // 创建一个数组来表示每棵树是否成活，0表示成活，1表示未成活
        int[] nums = new int[total];

        // 初始化数组，所有元素设为0，表示所有树最初都是成活的
        Arrays.fill(nums, 0);

        // 初始化数组，所有元素设为0，表示所有树最初都是成活的
        for (int i = 0; i < deadCount; i++) {
            int num = sc.nextInt();
            nums[num - 1] = 1; // 树的编号从1开始，因此需要减1
        }
        // 读取可以补种的树的数量
        int supplementCount = sc.nextInt();

        // 初始化滑动窗口的左右边界
        int left = 0;
        int maxLen = 0; // 用于存储最大连续成活区域的长度
        int sumLeft = 0; // 滑动窗口左边界的未成活树数量
        int sumRight = 0; // 滑动窗口右边界的未成活树数量
        // 遍历所有的树，right代表滑动窗口的右边界
        for (int right = 0; right < total; right++) {
            sumRight += nums[right]; // 更新右边界的未成活树数量

            // 如果窗口内的未成活树数量大于可以补种的数量
            while (sumRight - sumLeft > supplementCount) {
                sumLeft += nums[left]; // 缩小窗口，左边界右移
                left++;
            }

            // 更新最大成活区域的长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        // 更新最大成活区域的长度
        System.out.println(maxLen);

    }


}
