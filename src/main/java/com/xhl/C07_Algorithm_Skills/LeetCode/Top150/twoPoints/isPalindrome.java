package com.xhl.C07_Algorithm_Skills.LeetCode.Top150.twoPoints;

/**
 * @Author: xhl
 * @Date: 2026-04-17 10:29
 * @Description: 验证回文串 isPalindrome
 */
public class isPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        isPalindrome ip = new isPalindrome();

        System.out.println("this sentence is "+ip.isPalindrome(s));
    }
    /**
     *  方法一: 筛选 + 判断
     * */
    public boolean isPalindrome(String s) {
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer sgood_rev = new StringBuffer(sb).reverse();
        return sb.toString().equals(sgood_rev.toString());
    }
    /**
     *  方法二: 双指针
     * */
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
}
