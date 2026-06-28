package com.xhl.Career_Growth.SelfSummary.BaseAL.TwoPointers;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-23 20:52
 * @Description: 125. 验证回文串
 */
public class isPalindrome {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        isPalindrome is = new isPalindrome();
        boolean palindrome = is.isPalindrome(s);
        System.out.printf("是否是 回文串？"+palindrome);
    }
    //方法一：筛选 + 判断
    public boolean isPalindrome(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                sb.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer snew = new StringBuffer(sb).reverse();
        return sb.toString().equals(snew.toString());
    }
    // 方法二 ：双指针
    public boolean isPalindrome1(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        int n = sgood.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(sgood.charAt(left)) != Character.toLowerCase(sgood.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
    //方法二：在原字符串上直接判断
    public boolean isPalindrome2(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

}
