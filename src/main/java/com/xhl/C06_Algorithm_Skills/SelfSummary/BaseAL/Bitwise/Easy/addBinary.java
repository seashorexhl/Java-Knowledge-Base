package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Bitwise.Easy;

import java.math.BigInteger;

/**
 * @Author: xhl
 * @Date: 2026-06-25 03:28
 * @Description:
 */
public class addBinary {
    static void main() {
        String a = "11";
        String b = "1";
        addBinary ab =new addBinary();
        System.out.println(ab.addBinary(a,b));
    }
    // 朴素解法 先将 a 和 b 转化成十进制数，求和后再转化为二进制数
    public String addBinary(String a, String b) {
        String res = Integer.toBinaryString(
                Integer.parseInt(a,2) + Integer.parseInt(b,2));
        return  res ;
    }
    // 方法一：模拟
    public String addBinary1(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }
    //方法二：位运算
    public String addBinary2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);

        while (!y.equals(BigInteger.ZERO)) {
            BigInteger answer = x.xor(y);
            BigInteger carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }

        return x.toString(2);
    }

}
