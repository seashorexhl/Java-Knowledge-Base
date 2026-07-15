package com.xhl.C07_Algorithm_Skills.ODExam.TestPaperA.answer;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-06 12:02
 * @Description: 03 考勤信息 特异性的双端队列 猜数字
 *
 */
public class Attendance {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 读取测试用例数量
        int testCases = Integer.parseInt(input.nextLine().trim());
        // 使用StringBuilder来构建所有测试用例的输出结果
        StringBuilder results = new StringBuilder();

        // 遍历处理每个测试用例
        while (testCases-- > 0) {
        // 读取并分割每个测试用例的考勤记录
        String[] attendanceRecords = input.nextLine().trim().split(" ");
        // 判断是否能获得考勤奖，并追加结果到results
        results.append(canReceiveAward(attendanceRecords) ? "true" : "false");
        // 如果还有剩余测试用例，追加一个空格分隔
        if (testCases > 0) {
            results.append(" ");
        }
    }
        // 输出所有测试用例的结果
        System.out.println(results.toString());
        // 关闭Scanner
        input.close();
}

    // 判断是否能获得考勤奖的方法
    private static boolean canReceiveAward(String[] records) {
        // 缺勤次数计数器
        int absentCount = 0;
        // 遍历考勤记录
        for (int i = 0; i < records.length; i++) {
            // 如果记录为缺勤，增加缺勤计数
            if ("absent".equals(records[i])) {
                absentCount++;
                // 如果缺勤超过1次，返回false
                if (absentCount > 1) return false;
            }
            // 如果记录为迟到或早退，且前一天也是迟到或早退，返回false
            if ("late".equals(records[i]) || "leaveearly".equals(records[i])) {
                if (i > 0 && ("late".equals(records[i - 1]) || "leaveearly".equals(records[i - 1]))) {
                    return false;
                }
            }
            // 检查任意连续7天的考勤记录
            if (i >= 6) {
                int countIn7Days = 0;
                // 计算连续7天内非正常上班的天数
                for (int j = i - 6; j <= i; j++) {
                    if (!"present".equals(records[j])) {
                        countIn7Days++;
                    }
                }
                // 如果连续7天内非正常上班超过3天，返回false
                if (countIn7Days > 3) return false;
            }
        }
        // 如果所有条件都满足，返回true
        return true;
    }
}
