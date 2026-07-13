package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.String.hard;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-11 21:00
 * @Description: 整数转罗马数字
 *  输入：3749
 *  输出：MMMDCCXLIX
 */
public class intToRoman {
    // 方法二：硬编码数字 模运算+除法运算
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    static void main() {
        Scanner sc = new Scanner(System.in);
        int num  =sc.nextInt();
        String s = intToRoman(num);
        System.out.println("整数转罗马数字结果为: "+s);
    }

    //方法一：模拟
    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    public String intToRoman1(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }
}
