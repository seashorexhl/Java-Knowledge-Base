package com.xhl.C06_Algorithm_Skills.LeetCode.Beginner;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-05 03:41
 * @Description: 统计字符串中 各类字符的数量 最长连续子串
 */
public class CountCharacter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] nums = new int[4];
        int SmallCount =0;
        int BigCount =0;
        int NumCount =0;
        int CharCount =0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                SmallCount ++;
            }else if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                BigCount ++;
            }else  if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                NumCount ++;
            }else  {
                CharCount ++;
            }
        }
        nums[0] = BigCount;
        nums[1] = SmallCount;
        nums[2] = NumCount;
        nums[3] = CharCount;
        for (int i = 0; i < 4; i++) {
            System.out.print(nums[i]+" ");
        }
        sc.close();
    }


// 优化后
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        // 直接用数组来计数，省去额外的变量
        int[] nums = new int[4];

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                nums[0]++; // 大写字母
            } else if (c >= 'a' && c <= 'z') {
                nums[1]++; // 小写字母
            } else if (c >= '0' && c <= '9') {
                nums[2]++; // 数字
            } else {
                nums[3]++; // 其他字符
            }
        }

        // 按照题目要求，用空格隔开输出在同一行
        System.out.println(nums[0] + " " + nums[1] + " " + nums[2] + " " + nums[3]);
    }
}
