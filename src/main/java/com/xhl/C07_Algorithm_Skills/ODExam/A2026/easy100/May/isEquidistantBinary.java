package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:02
 * @Description: 等距二进制判断-100分
 *  位运算 + 字符串计解析
 * 题目描述：对于一个二进制数，我们定义相邻两个1之间0的数量为他们两个之间的距离，如1001011，相邻两个1之间的距离从左到右分别为2、1、0。
 * 现在如果一个整数转化为二进制数满足如下条件：
 * 1）包含不少于3个1
 * 2）所有相邻数字1之间的距离相同
 * 我们称之为等距二进制，如21（二进制为：10101)、60（二进制为：111100)、146（二进制为：10010010)。
 * 输入：现给定一个输入，整数0<=n<(2^31-1)
 * 输出：如果n是等距二进制，请输出它的距离，如果不是等距二进制，请输出﹣1
 */
public class isEquidistantBinary {
    static void main(String[] args) {
        int n = 5;
        int equidistantBinary = isEquidistantBinary(n);
        System.out.println("等距二进制，请输出它的距离:"+equidistantBinary);

    }
    // 位运算 + 字符串解析
    public static int isEquidistantBinary(int n) {
        if (n == 0) {
            return -1;
        }
        String binStr = Integer.toBinaryString(n);
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < binStr.length(); i++) {
            if (binStr.charAt(i) == '1') {
                positions.add(i);
            }
        }
        if (positions.size() < 3) {
            return -1;
        }
        int d = positions.get(1) - positions.get(0) - 1;
        for (int i = 1; i < positions.size() - 1; i++) {
            int dist = positions.get(i+1) - positions.get(i) - 1;
            if (dist != d) {
                return -1;
            }
        }
        return d;

    }
}