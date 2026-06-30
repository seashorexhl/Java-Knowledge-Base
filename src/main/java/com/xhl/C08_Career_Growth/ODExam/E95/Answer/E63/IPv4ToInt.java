package com.xhl.C08_Career_Growth.ODExam.E95.Answer.E63;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-30 18:41
 * @Description: 01 IPv4地址转换成整数
 * 输入描述
 * 输入一行，虚拟IPv4地址格式字符串
 * 输出描述
 * 输出一行，按照要求输出整型或者特定字符
 */
public class IPv4ToInt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ipSections = sc.nextLine().split("#"); // 将输入的字符串按照"#"分割成4个小节
        if (ipSections.length != 4) { // 如果分割后的小节数量不等于4，则说明输入的IPv4地址格式不正确
            System.out.println("invalid IP");
            return; // 结束程序
        }
        // For each
        for (String section : ipSections) {
            if (!isNumeric(section)) { // 检查是否每部分都是数字
                System.out.println("invalid IP");
                return; // 结束程序
            }
            if (section.length() == 0) { // 检查是否有空字符
                System.out.println("invalid IP");
                return; // 结束程序
            }

            // 检查前导零的情况
            if (section.length() > 1 && section.charAt(0) == '0') {
                System.out.println("invalid IP");
                return; // 结束程序
            }
        }

        int firstSection = Integer.parseInt(ipSections[0]); // 将第一个小节转换为整数
        if (firstSection < 1 || firstSection > 128) { // 如果第一个小节的值不在1~128的范围内，则说明输入的IPv4地址格式不正确
            System.out.println("invalid IP");
            return; // 结束程序
        }

        for (int i = 1; i < 4; i++) { // 遍历后面的3个小节
            int sectionValue = Integer.parseInt(ipSections[i]); // 将当前小节转换为整数
            if (sectionValue < 0 || sectionValue > 255) { // 如果当前小节的值不在0~255的范围内，则说明输入的IPv4地址格式不正确
                System.out.println("invalid IP");
                return; // 结束程序
            }
        }

        long ipValue = 0; // 用于计算32位整数值
        for (int i = 0; i < 4; i++) {
            ipValue = ipValue * 256 + Integer.parseInt(ipSections[i]); // 每个小节对应一个字节，计算最终的整数值
        }

        System.out.println(ipValue); // 输出最终计算得到的32位整数
    }

    // 判断字符串是否为数字
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false; // 如果有非数字字符则返回false
            }
        }
        return true; // 全部为数字则返回true

    }
    public int IpV4ToInt(String ipAddress) {

        return 0;
    }
}
