package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Simulate;

/**
 * @Author: xhl
 * @Date: 2026-06-19 09:32
 * @Description: 各位相加
 *  给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */
public class addDigits {
    static void main() {
        int num = 88;

        addDigits ad = new addDigits();
        System.out.println(ad.addDigits(num));

    }
    /**
     *方法一 ：模拟
    */
    public int addDigits(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
    /**
     *方法二：数学 经典的数学问题（求数根 Digital Root）
     */
    public int addDigitsMathRoot(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
    /**
     * 方法三： 数学规律法  简洁
     * */
    public int addDigitsMath(int num) {
        return (num - 1) % 9 + 1;
    }
}
