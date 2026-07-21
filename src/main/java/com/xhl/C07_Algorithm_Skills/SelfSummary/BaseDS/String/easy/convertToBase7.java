package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.String.easy;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-23 13:12
 * @Description: 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 */
public class convertToBase7 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        convertToBase7 c7 = new convertToBase7();
        System.out.printf("转化成的7进制数为：" + c7.convertToBase7(n));

    }
    /**
     *  方法一：倒推 + 迭代
     * */

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean negative = num < 0;
        num = Math.abs(num);
        StringBuffer digits = new StringBuffer();
        while (num > 0) {
            digits.append(num % 7);
            num /= 7;
        }
        if (negative) {
            digits.append('-');
        }
        return digits.reverse().toString();
    }
}
