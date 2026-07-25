package com.xhl.C07_Algorithm_Skills.LeetCode.Beginner;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-05 03:59
 * @Description: 入门 字符串反转
 */
public class StringReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String s = new StringBuilder(str).reverse().toString();
            System.out.println(s);
        }
        sc.close();
    }
}
