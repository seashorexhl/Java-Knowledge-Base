package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-14 12:42
 * @Description: 题目一: 准备生日礼物 100分
 *  字符串处理 + 哈希表
 */
public class BirthdayCounter {
    static void main(String[] args) {
        int month = 7;
        String[] employees = {"A","B","C","D","E","F","G","H"};
        String[] birthdays = {"2026/01/25","2026/02/23","2026/03/24","2026/04/25","2026/05/25",
                "2026/06/25","2026/07/25","2026/07/25"};

        int i = countBirthdayGifts(month, employees, birthdays);
        System.out.println("i = " + i);
    }

    /**
     * 哈希表 + 字符串处理
     */
    public static int countBirthdayGifts(int month, String[] employees, String[] birthdays) {
        // 使用哈希表（或字典）存储员工姓名和生日信息，自动处理重复记录
        Map<String, String> employeeMap = new HashMap<>();
        // 遍历员工列表，将每个员工及其生日存入哈希表
        for (int i = 0; i < employees.length; i++) {
            employeeMap.put(employees[i], birthdays[i]);
        }
        // 统计哈希表中生日月份等于目标月份的员工数量
        int count = 0;
        // 统计哈希表中生日月份等于目标月份的员工数量
        for (Map.Entry<String, String> entry : employeeMap.entrySet()) {
            String[] dateParts = entry.getValue().split("/");
            int birthMonth = Integer.parseInt(dateParts[1]);
            if (birthMonth == month) {
                count++;
            }
        }
        return count;
    }

}