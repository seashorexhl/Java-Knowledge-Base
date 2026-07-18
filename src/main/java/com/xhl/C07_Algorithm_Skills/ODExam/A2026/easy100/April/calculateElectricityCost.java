package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-15 07:39
 * @Description: 核心代码编程-计费时段计算-100分
 *  字符串解析 + 时间处理逻辑 + 区间 求交算法 + 区间交集 + 业务规则到代码的映射
 * 第一档：用电时间在每天的12:00-13:30和17:30-18:00
 * 第二档：每天从0:00起的，且不在第一档时段内的，累积的10小时
 * 第三档： 其他时段
 */
public class calculateElectricityCost {
    static void main(String[] args) {
        String input = "8:00 23:30";
        List<Integer> list = calculateElectricityCost(input);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
    //
    public static List<Integer> calculateElectricityCost(String input) {
        // 解析输入字符串
        String[] times = input.replace("\"", "").split(" ");
        String startStr = times[0];
        String endStr = times[1];

        // 转换时间
        String[] startParts = startStr.split(":");
        String[] endParts = endStr.split(":");
        int startMinute = Integer.parseInt(startParts[0]) * 60 + Integer.parseInt(startParts[1]);
        int endMinute = Integer.parseInt(endParts[0]) * 60 + Integer.parseInt(endParts[1]);

        // 处理0分钟情况
        if (startMinute == endMinute) {
            return Arrays.asList(0, 0, 0);
        }
        // 第一档时段
        int[][] tier1Intervals = {
                {12 * 60, 13 * 60 + 30},
                {17 * 60 + 30, 18 * 60}
        };

        // 计算第一档时长
        int first = 0;
        for (int[] interval : tier1Intervals) {
            int low = Math.max(startMinute, interval[0]);
            int high = Math.min(endMinute, interval[1]);
            if (low < high) {
                first += high - low;
            }
        }

        // 计算第二档和第三档
        int totalDuration = endMinute - startMinute;
        int nonFirst = totalDuration - first;
        int second = Math.min(nonFirst, 600);
        int third = nonFirst - second;

        return Arrays.asList(first, second, third);

    }
}
